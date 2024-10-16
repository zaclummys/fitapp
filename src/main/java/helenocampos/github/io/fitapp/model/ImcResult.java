package helenocampos.github.io.fitapp.model;

public class ImcResult {
    String classification;
    double imc;

    public ImcResult(String classification, double imc){
        this.classification = classification;
        this.imc = imc;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }    
}
