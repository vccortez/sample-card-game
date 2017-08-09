package com.tardigrade.utils;

public interface StatusListener{
    void onStart(Object object);             // Quando começa a verificação
    void onUpdate(Object object);            // Quando termina a verificação e precisa atualizar os dados
    void onEnd(Object object);               // Quando tudo está feito
}