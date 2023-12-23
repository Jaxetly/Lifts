package ru.kostya;

public interface Call {
    public void call(int _floor, Post _post) throws CallBusyElevator, AllElevatorIsBusy;
}
