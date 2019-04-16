package android.example.listofpatients;

public class Patient {

    public String name;
    public String ward;
    public int bedNumber;


    public Patient(String name, String ward, int bedNumber){

        this.name = name;
        this.ward = ward;
        this.bedNumber = bedNumber;

    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getWard() {
        return this.ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public int getBedNumber() {
        return this.bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }




}
