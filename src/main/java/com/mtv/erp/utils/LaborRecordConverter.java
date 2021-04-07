package com.mtv.erp.utils;

import com.mtv.erp.model.Hours;
import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.model.User;
import com.mtv.erp.request.HoursGetUserDtoRequest;
import com.mtv.erp.request.UserGetFromDateDtoRequest;
import com.mtv.erp.request.UsersGetFromDateDtoRequest;
import com.mtv.erp.response.HoursGetUserDtoResponse;
import com.mtv.erp.response.UserHoursGetUserDtoResponse;
import com.mtv.erp.response.planfixResponse.PlanfixLaborRecord;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LaborRecordConverter {

    public static List<UserHoursGetUserDtoResponse> convertLaborRecord(List<LaborRecord> laborRecords) {
        List<UserHoursGetUserDtoResponse> list = new ArrayList<>();
        for (LaborRecord laborRecord : laborRecords) {
            list.add(new UserHoursGetUserDtoResponse(laborRecord.getId(), laborRecord.getUser(), laborRecord.getDate(), laborRecord.getHours(), String.valueOf(laborRecord.getTaskId()), laborRecord.getTaskTitle(), String.valueOf(laborRecord.getProjectId()), laborRecord.getProjectTitle()));
        }
        return list;
    }

    public static List<HoursGetUserDtoResponse> convertDtoHours(List<Hours> hours) {
        List<HoursGetUserDtoResponse> list = new ArrayList<>();
        for (Hours hour : hours) {
            list.add(new HoursGetUserDtoResponse(hour.getId(), hour.getUser(), hour.getDate(), hour.getHours(), hour.isSaved(), hour.getType()));
        }
        return list;
    }

    public static List<Hours> convertHours(List<Hours> hours, LocalDate date, User user) {
        Hours[] monthHours = new Hours[date.lengthOfMonth()];
        for (int i = 0; i < monthHours.length; i++) {
            monthHours[i] = new Hours("0", LocalDate.of(date.getYear(), date.getMonth(), i + 1), user, 5);
        }
        if (hours == null || hours.size() == 0) {
            return Arrays.asList(monthHours);
        }
        for (Hours elem : hours) {
            int index = elem.getDate().getDayOfMonth() - 1;
            if (monthHours[index].getHours().equals("0")) {
                monthHours[index] = elem;
            }
        }
        return Arrays.asList(monthHours);
    }

    public static List<Hours> convertToHours(List<LaborRecord> laborRecords) {
        List<Hours> list = new ArrayList<>();
        for (LaborRecord laborRecord : laborRecords) {
            list.add(new Hours(laborRecord.getId(), laborRecord.getUser(), laborRecord.getDate(), String.valueOf(laborRecord.getHours()), 1));
        }
        return uniqueDayHours(list);
    }

    private static List<Hours> uniqueDayHours(List<Hours> hours) {
        List<Integer> deleteIds = new ArrayList<>();
        List<Hours> list = new ArrayList<>(hours);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                Hours hours1 = list.get(i);
                Hours hours2 = list.get(j);
                float scale = (float) Math.pow(10, 1);
                if (i == 0) {
                    float num = Float.parseFloat(hours2.getHours());
                    if(num != 0) {
                        hours2.setHours(String.valueOf(Math.round((num) * scale) / scale));
                    }
                }
                if (hours1.getUser().getFirstname().equals(hours2.getUser().getFirstname()) && hours1.getUser().getLastname().equals(hours2.getUser().getLastname()) && hours1.getUser().getEmail().equals(hours2.getUser().getEmail()) && hours1.getDate().equals(hours2.getDate()))
                    if (i != j) {
                        if (!(deleteIds.contains(i) || deleteIds.contains(j))) {
                            float num = Float.parseFloat(hours1.getHours()) + Float.parseFloat(hours2.getHours());
                            hours1.setHours(String.valueOf(num));
                            deleteIds.add(j);
                        }
                    }
            }
        }
        for (int index : deleteIds) {
            list.set(index, null);
        }
        list.removeIf(Objects::isNull);
        return list;
    }

    public static List<LaborRecord> fromPlanfix(List<PlanfixLaborRecord> planfixLaborRecords) {
        List<LaborRecord> laborRecords = new ArrayList<>();
        for (PlanfixLaborRecord planfixLaborRecord : planfixLaborRecords) {
            planfixLaborRecord.setLocalDateTime();
            LocalTime time = planfixLaborRecord.getLaborSpan();
            float hours = (float) planfixLaborRecord.getLaborSpan().getHour() + (float) planfixLaborRecord.getLaborSpan().getMinute() / 60;
            laborRecords.add(new LaborRecord(new User(planfixLaborRecord.getFirstname(), planfixLaborRecord.getLastname()), planfixLaborRecord.getStartTime().toLocalDate(), (float) planfixLaborRecord.getLaborSpan().getHour() + (float) planfixLaborRecord.getLaborSpan().getMinute() / 60, (int) planfixLaborRecord.getTaskId(), planfixLaborRecord.getTaskTitle(), (int) planfixLaborRecord.getProjectId(), planfixLaborRecord.getProjectTitle()));
        }
        return laborRecords;
    }

    public static List<Hours> convertChangeHours(UsersGetFromDateDtoRequest request) {
        List<Hours> hourList = new ArrayList<>();
        for (UserGetFromDateDtoRequest user : request.getUserList()) {
            for (HoursGetUserDtoRequest hours : user.getHours()) {
                if (hours.getId() != 0 || !hours.getHours().equals("0") || hours.getType() != 5) {
                    hourList.add(new Hours(hours.getId(), hours.getUser(), hours.getDate(), hours.getHours(), hours.getType()));
                }
            }
        }
        return hourList;
    }
}
