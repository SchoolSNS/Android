package com.example.hischool.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ3\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0000\u00a2\u0006\u0002\b\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/example/hischool/module/SignUpDialog;", "", "()V", "connectionFail", "", "context", "Landroid/content/Context;", "sweetAlertDialog", "Lcn/pedant/SweetAlert/SweetAlertDialog;", "connectionSuccess", "response", "Lretrofit2/Response;", "Lcom/example/hischool/data/SignUpResponse;", "navController", "Landroidx/navigation/NavController;", "connectionSuccess$app_debug", "app_debug"})
public final class SignUpDialog {
    
    public final void connectionSuccess$app_debug(@org.jetbrains.annotations.NotNull()
    retrofit2.Response<com.example.hischool.data.SignUpResponse> response, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    cn.pedant.SweetAlert.SweetAlertDialog sweetAlertDialog) {
    }
    
    public final void connectionFail(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    cn.pedant.SweetAlert.SweetAlertDialog sweetAlertDialog) {
    }
    
    public SignUpDialog() {
        super();
    }
}