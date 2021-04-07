package com.mtv.erp.service;

import com.mtv.erp.dao.HoursDao;
import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Hours;
import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.model.User;
import com.mtv.erp.request.HoursGetUserDtoRequest;
import com.mtv.erp.request.UserGetFromDateDtoRequest;
import com.mtv.erp.request.UsersGetFromDateDtoRequest;
import com.mtv.erp.response.EmptyResponse;
import com.mtv.erp.response.HoursGetUserDtoResponse;
import com.mtv.erp.response.UserGetFromDateDtoResponse;
import com.mtv.erp.response.planfixResponse.PlanfixLaborRecord;
import com.mtv.erp.utils.LaborRecordConverter;
import com.mtv.erp.utils.MonthYearConverter;
import com.mtv.erp.utils.Planfix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HoursService {

    @Autowired
    HoursDao hoursDao;

    @Autowired
    UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoursService.class);

    @Scheduled(cron = "0 0 1 * * *")
    public EmptyResponse update() throws ServerException {
        LOGGER.debug("Hours UPDATE");
        Planfix planfix = new Planfix();
        LocalDate now = LocalDate.now();
        LocalDate from;
        List<PlanfixLaborRecord> planfixLaborRecords;
        if (now.getDayOfMonth() <= 8) {
            from = LocalDate.of(now.getYear(), now.getMonthValue() - 1, 1);
        } else {
            from = now.withDayOfMonth(1);
        }
        planfixLaborRecords = planfix.getHours(from, now);
        LOGGER.debug("Hours in Planfix: {}", planfixLaborRecords.size());
        List<LaborRecord> laborRecords = hoursDao.getFromDate(from, now);
        List<LaborRecord> laborsUpdate = new ArrayList<>();
        List<LaborRecord> laborsDelete = new ArrayList<>(laborRecords);
        List<LaborRecord> laborsNotAdd = new ArrayList<>();
        List<LaborRecord> laborRecordsFromPlanfix = LaborRecordConverter.fromPlanfix(planfixLaborRecords);
        List<Integer> deleteIds = new ArrayList<>();
        List<User> users = userDao.getAll();
        for (LaborRecord laborRecord : laborRecordsFromPlanfix) {
            for (int i = 0; i < users.size(); i++) {
                if (laborRecord.getUser().getFirstname().equals(users.get(i).getFirstname()) && laborRecord.getUser().getLastname().equals(users.get(i).getLastname())) {
                    laborRecord.setUser(users.get(i));
                    break;
                }
                if (i == users.size() - 1) {
                    laborsDelete.add(laborRecord);
                }
            }
        }
        laborRecordsFromPlanfix.removeAll(laborsDelete);
        laborsDelete.clear();
        for (int i = 0; i < laborRecordsFromPlanfix.size(); i++) {
            for (int j = 0; j < laborRecordsFromPlanfix.size(); j++) {
                LaborRecord labor1 = laborRecordsFromPlanfix.get(i);
                LaborRecord labor2 = laborRecordsFromPlanfix.get(j);
                if (labor1.getUser().getFirstname().equals(labor2.getUser().getFirstname()) && labor1.getUser().getLastname().equals(labor2.getUser().getLastname()) && labor1.getUser().getEmail().equals(labor2.getUser().getEmail()) && labor1.getDate().equals(labor2.getDate()) && labor1.getTaskId() == labor2.getTaskId() && labor1.getProjectId() == labor2.getProjectId())
                    if (i != j) {
                        if (!(deleteIds.contains(i) || deleteIds.contains(j))) {
                            labor1.setHours(labor1.getHours() + labor2.getHours());
                            deleteIds.add(j);
                        }
                    }
            }
        }
        for (int index : deleteIds) {
            laborRecordsFromPlanfix.set(index, null);
        }
        laborRecordsFromPlanfix.removeIf(Objects::isNull);
        for (int i = 0; i < laborRecordsFromPlanfix.size(); i++) {
            if (laborRecords.size() == 0) {
                break;
            }
            for (int j = 0; j < laborRecords.size(); j++) {
                boolean equals = laborRecordsFromPlanfix.get(i).getProjectId() == laborRecords.get(j).getProjectId() && laborRecordsFromPlanfix.get(i).getTaskId() == laborRecords.get(j).getTaskId() && laborRecordsFromPlanfix.get(i).getDate().equals(laborRecords.get(j).getDate()) && laborRecordsFromPlanfix.get(i).getUser().getFirstname().equals(laborRecords.get(j).getUser().getFirstname()) && laborRecordsFromPlanfix.get(i).getUser().getLastname().equals(laborRecords.get(j).getUser().getLastname()) && laborRecordsFromPlanfix.get(i).getUser().getEmail().equals(laborRecords.get(j).getUser().getEmail());
                if (equals) {
                    if (!(laborRecordsFromPlanfix.get(i).getHours() == laborRecords.get(j).getHours())) {
                        laborRecords.get(j).setHours(laborRecordsFromPlanfix.get(i).getHours());
                        laborsUpdate.add(laborRecords.get(j));
                    }
                    laborsNotAdd.add(laborRecordsFromPlanfix.get(i));
                    break;
                }
            }
        }
        laborRecordsFromPlanfix.removeAll(laborsNotAdd);
        laborsDelete.removeAll(laborsNotAdd);
        laborsDelete.removeAll(laborsUpdate);
        LOGGER.debug("UserHours add: {}", laborRecordsFromPlanfix.size());
        for (LaborRecord laborRecord : laborRecordsFromPlanfix) {
            System.out.println(laborRecord.getUser().getId() + " " + laborRecord.getUser().getFirstname() + " " + laborRecord.getUser().getLastname());
        }
        if (laborRecordsFromPlanfix.size() > 0) {
            hoursDao.insertAll(laborRecordsFromPlanfix);
            hoursDao.insertAllHours(LaborRecordConverter.convertToHours(laborRecordsFromPlanfix));
        }
        LOGGER.debug("UserHours update: {}", laborsUpdate.size());
        for (LaborRecord laborRecord : laborsUpdate) {
            hoursDao.update(laborRecord);
        }
        for (Hours hours : LaborRecordConverter.convertToHours(laborsUpdate)) {
            hoursDao.updateHours(hours);
        }
        LOGGER.debug("UserHours delete: {}", laborsDelete.size());
        for (LaborRecord laborRecord : laborsDelete) {
            hoursDao.delete(laborRecord.getId());
        }
        for (Hours laborRecord : LaborRecordConverter.convertToHours(laborsDelete)) {
            for (Hours hours : hoursDao.getFromDateHours(from, now)) {
                if (laborRecord.getDate().equals(hours.getDate()) && laborRecord.getUser().equals(hours.getUser())) {
                    hours.setHours(String.valueOf(Float.parseFloat(hours.getHours()) - Float.parseFloat(laborRecord.getHours())));
                    hoursDao.updateHours(hours);
                }
            }
            hoursDao.deleteHours(laborRecord.getId());
        }
        return new EmptyResponse();
    }

    public List<LaborRecord> getFromDate(String monthYear) throws ServerException {
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = (from.getMonthValue() < LocalDate.now().getMonthValue() && from.getYear() <= LocalDate.now().getYear()) ? from.withDayOfMonth(from.lengthOfMonth()) : LocalDate.now();
        return hoursDao.getFromDate(from, to);
    }

    public boolean saveChanges(UsersGetFromDateDtoRequest request, List<HoursGetUserDtoResponse> oldHours) throws ServerException {
        List<Hours> hours = LaborRecordConverter.convertChangeHours(request);
        for (Hours elem : hours) {
            for (HoursGetUserDtoResponse old : oldHours) {
                String elemHours;
                String oldElemHours;
                try {
                    oldElemHours = String.valueOf(Float.parseFloat(old.getHours()));
                    elemHours = String.valueOf(Float.parseFloat(elem.getHours()));
                } catch (NumberFormatException ex) {
                    elemHours = elem.getHours();
                    oldElemHours = old.getHours();
                }
                if (elem.getId() == old.getId() && (!elemHours.equals(oldElemHours) || elem.getType() != old.getType())) {
                    hoursDao.saveChanges(elem);
                    break;
                }
            }
            if (elem.getId() == 0 && (!elem.getHours().equals("0") || elem.getType() != 5)) {
                hoursDao.insertChanges(elem);
            }
        }
        return true;
    }
}
