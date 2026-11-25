public class LEmployee {
    private int empId;
    private String empCode;
    private String empName;

    public LEmployee() {
    }

    public LEmployee(int empId, String empCode, String empName) {
        this.empId = empId;
        this.empCode = empCode;
        this.empName = empName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "LEmployee{empId=" + empId
                + ", empCode='" + empCode + "'"
                + ", empName='" + empName + "'}";
    }
}
