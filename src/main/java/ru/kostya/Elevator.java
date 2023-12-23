package ru.kostya;

public abstract class Elevator{
    protected Status status;
    protected int floorNow;
    protected int floorNeed;
    protected MoveStatus moveStatus;
    protected Elevator next;
    protected ElevatorPurpose purpose;

    public Status getStatus() {
        return status;
    }

    public ElevatorPurpose getPurpose() {
        return purpose;
    }

    Elevator(){
        floorNow = 1;
        floorNeed = floorNow;
        moveStatus = MoveStatus.stands;
        status = Status.free;
    }

    //Устанавливаем куда движется лифт, с проверкой может ли он двигаться и будет ли он двигаться
    public void call(int _floor) throws CallBusyElevator {
        if (status == Status.busy)
            throw new CallBusyElevator("Elevator still busy, he cant go to " + _floor + " floor");

        floorNeed = _floor;
        if (floorNeed > floorNow) {
            moveStatus = MoveStatus.up;
            status = Status.busy;
        } else if (floorNeed < floorNow) {
            moveStatus = MoveStatus.down;
            status = Status.busy;
        } else {
            moveStatus = MoveStatus.stands;
            status = Status.free;
        }
    }

    //Движение с проверкой того, что лифт приехал и освободился
    public void move() {
        if (moveStatus != MoveStatus.stands) {
            if (moveStatus == MoveStatus.up) {
                floorNow++;
            } else {
                floorNow--;
            }
            if (floorNow==floorNeed){
                moveStatus = MoveStatus.stands;
                status = Status.free;
            }
        }
    }
}
