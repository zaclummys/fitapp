package helenocampos.github.io.fitapp.model;

public class ImcGenderResult extends ImcResult{

    private String gender;
    private String requestUrl;

    public ImcGenderResult(String classification, double imc, String gender, String genderUrl){
        super(classification, imc);
        this.gender = gender;
        this.requestUrl = genderUrl;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
    
    public String getRequestUrl() {
        return this.requestUrl;
    }

    public void setRequestUrl(String requestUrl){
        this.requestUrl = requestUrl;
    }
}
