package com.cloudcoders.gestaca.persistance;

import com.cloudcoders.gestaca.logic.ITaughtCourseDAO;
import com.cloudcoders.gestaca.model.Course;
import com.cloudcoders.gestaca.model.Office;
import com.cloudcoders.gestaca.model.TaughtCourse;
import com.cloudcoders.gestaca.model.Teacher;
import com.cloudcoders.gestaca.persistance.dal.FileDAL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TaughtCourseDAOImpl implements ITaughtCourseDAO {

  private FileDAL parser;

  public TaughtCourseDAOImpl(FileDAL p) {
    this.parser = p;
  }

  @Override
  public void add(TaughtCourse taughtCourse) {
    JSONObject aux = new JSONObject();
    aux.put("quota", taughtCourse.getQuota());
    aux.put("sessionDuration", taughtCourse.getSessionDuration());
    aux.put("startDate", taughtCourse.getStartDate().getTime());
    aux.put("totalPrice", taughtCourse.getTotalPrice());
    aux.put("teachingday", taughtCourse.getTeachingday());
    aux.put("endDate", taughtCourse.getEndDate().getTime());
    int newId = (int) System.currentTimeMillis();
    aux.put("id", newId);
    aux.put("office", taughtCourse.getOffice());
    aux.put("teacher", taughtCourse.getTeacher());
    aux.put("course", taughtCourse.getCourse());
    aux.put("enrollments", taughtCourse.getEnrollments());

    JSONArray jsonArray = null;

//    jsonArray = parser.readFile("TaughtCourse.json");

    boolean idIsUnique = true;
    for (Iterator<Object> iterator = jsonArray.iterator(); iterator.hasNext() && idIsUnique; ) {
      Object o = iterator.next();
      JSONObject jsonObject = (JSONObject) o;
      if (((int) jsonObject.get("id")) == taughtCourse.getId()) {
        idIsUnique = false;
      }
    }

    if(idIsUnique) {
      jsonArray.put(aux);
//      parser.writeFile("TaughtCourse.json", jsonArray);
    }

  }

  @Override
  public TaughtCourse remove(TaughtCourse taughtCourse) {
    return null;
  }

  @Override
  public TaughtCourse get(int id) {
    return null;
  }

  @Override
  public List<TaughtCourse> getAll() {
    List<TaughtCourse> taughtCourses = new ArrayList<>();
    JSONArray jsonArray = null;

//    jsonArray = parser.readFile("TaughtCourse.json");

    for(Object o : jsonArray) {
      JSONObject jsonObject = (JSONObject) o;
      int quota = (int) jsonObject.get("quota");
      int sessionDuration = (int) jsonObject.get("sessionDuration");
      Date startDate = new Date((long) jsonObject.get("startDate"));
      int totalPrice = (int) jsonObject.get("totalPrice");
      String teachingday = (String) jsonObject.get("teachingday");
      Date endDate = new Date((long) jsonObject.get("endDate"));
      int id = (int) jsonObject.get("id");
      Office office = (Office) jsonObject.get("office");
      Teacher teacher = (Teacher) jsonObject.get("teacher");
      Course course = (Course) jsonObject.get("course");

      TaughtCourse aux = new TaughtCourse(quota,
            sessionDuration,
            startDate,
            totalPrice,
            teachingday,
            endDate,
            id,
            office,
            teacher,
            course);

      taughtCourses.add(aux);
    }
    return taughtCourses;
  }
}

