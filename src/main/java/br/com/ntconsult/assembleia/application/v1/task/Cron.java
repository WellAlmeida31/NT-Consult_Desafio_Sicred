package br.com.ntconsult.assembleia.application.v1.task;

public interface Cron {
    String A_CADA_5_MINUTOS = "0 0/5 * * * ?";
    String A_CADA_1_MINUTO = "0 0/1 * * * ?";
}
