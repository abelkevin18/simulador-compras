package com.bcp.expose.web;

public class Prueba {
  /*public static void main(String[] args) {
    Double tea = 41.75;
    //calculo TEM
    tea = tea / 100;
    System.out.println(tea);

    float expTem = (float) 1 / 12;
    System.out.println(expTem);

    Double tem = Math.pow(1 + tea, expTem) - 1;
    //tem = tem * 100;
    System.out.println("tem: " + tem);

    //calculo FRC

    Integer numeroCuota = 10;

    Double dividendoFrc = tem*Math.pow(1+tem, numeroCuota);
    Double divisorFrc = Math.pow(1+tem, numeroCuota) - 1;

    Double frc = dividendoFrc / divisorFrc;
    System.out.println("frc: "+ frc);

    //Calculo cuota previa
    Double monto = 2000.0;
    Double cuotaPrevia = monto*frc;
    System.out.println("cuota previa: "+cuotaPrevia);

    //Calculo ITF
    Double itf = (0.005/100)*cuotaPrevia;
    System.out.println("itf: "+itf);

    //Calculo cuota final
    double cuotaFinal = cuotaPrevia + itf;
    System.out.println("cuotaFinal: "+cuotaFinal);



  }*/

  /*public static void main(String[] args) {
    Double cuota = calcularImporteMensual(8.33, 12, 1000.0);
    Double cuota2 = calcularImporteMensual(7.5, 12, 6000.0);
    System.out.println("monto primera cuota: "+cuota);
  }*/

  private static Double calcularImporteMensual(Double tea, Integer numeroCuotas, Double monto) {
    Double tem = calcularTem(tea);
    Double frc = calcularFrc(numeroCuotas, tem);
    return calcularCuota(monto, frc);
  }

  private static Double calcularTem(Double tea) {
    tea= tea/100;
    float expTem = (float) 1/12;
    return Math.pow(1+tea,expTem) - 1;
  }

  private static Double calcularFrc(Integer numeroCuotas, Double tem) {
    Double dividendoFrc = tem*Math.pow(1+tem, numeroCuotas);
    Double divisorFrc = Math.pow(1+tem, numeroCuotas) - 1;
    return dividendoFrc / divisorFrc;
  }

  private static Double calcularCuota(Double monto, Double frc){
    Double cuotaPrevia = monto*frc;
    Double itf = (0.005/100)*cuotaPrevia;
    return cuotaPrevia + itf;
  }
}
