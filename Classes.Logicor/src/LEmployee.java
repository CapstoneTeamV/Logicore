public class LEmployee {
    private int empId;
    private String empCode;
    private String empName;
    private Integer empOrdStatusId;
    private Integer empRctStatusId;

    public LEmployee() {
    }

    public LEmployee(int empId, String empCode, String empName,
                     Integer empOrdStatusId, Integer empRctStatusId) {
        this.empId = empId;
        this.empCode = empCode;
        this.empName = empName;
        this.empOrdStatusId = empOrdStatusId;
        this.empRctStatusId = empRctStatusId;
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

    public Integer getEmpOrdStatusId() {
        return empOrdStatusId;
    }

    public void setEmpOrdStatusId(Integer empOrdStatusId) {
        this.empOrdStatusId = empOrdStatusId;
    }

    public Integer getEmpRctStatusId() {
        return empRctStatusId;
    }

    public void setEmpRctStatusId(Integer empRctStatusId) {
        this.empRctStatusId = empRctStatusId;
    }

    @Override
    public String toString() {
        return "LEmployee{empId=" + empId
                + ", empCode='" + empCode + "'"
                + ", empName='" + empName + "'"
                + ", empOrdStatusId=" + empOrdStatusId
                + ", empRctStatusId=" + empRctStatusId + '}';
    }
}
