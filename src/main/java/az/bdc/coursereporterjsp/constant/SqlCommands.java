package az.bdc.coursereporterjsp.constant;

public final class SqlCommands {

    public static class Student {
        public final static String SELECT_ALL = "select * from student";
        public final static String INSERT_INTO = "insert into student(name, surname, fullname, birthdate, phone_number, pincode, create_date, update_date) values(?,?,?,?,?,?,?,?)";
        public final static String DELETE_BY_ID = "delete from student where id=?";
        public final static String UPDATE_SET = "update student set name=?, surname=?, fullname=?, birthdate=?, phone_number=?, pincode=?, update_date=? where id = ?";

    }

}

