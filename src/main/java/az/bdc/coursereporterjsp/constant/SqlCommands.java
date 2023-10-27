package az.bdc.coursereporterjsp.constant;

public final class SqlCommands {

    public static class Student {
        public final static String SELECT_ALL = "select * from students";
        public final static String INSERT_INTO = "insert into students(name, surname, fullname, birthdate, phone_number, pincode, create_date, update_date) values(?,?,?,?,?,?,?,?)";
        public final static String DELETE_BY_ID = "delete from students where id=?";
        public final static String UPDATE_SET = "update students set name=?, surname=?, fullname=?, birthdate=?, phone_number=?, pincode=?, update_date=? where id = ?";
        public final static String GET_BY_ID = "select * from students where id=?";

    }

}

