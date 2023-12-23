package ru.kostya;

public class ElevatorList implements Call, Free {
    protected Elevator current;
    protected int countElevator;

    //создаем нужное количество лифтов
    ElevatorList(int _countEven, int _countOdd, int _countEmployee) {
        countElevator = _countEmployee + _countEven + _countOdd;
        current = new ElevatorEven();
        _countEven--;

        Elevator head = current;
        for (int i = 0; i < _countEven; i++) {
            current.next = new ElevatorEven();
            current = current.next;
        }
        for (int i = 0; i < _countOdd; i++) {
            current.next = new ElevatorOdd();
            current = current.next;
        }
        for (int i = 0; i < _countEmployee; i++) {
            current.next = new ElevatorEmployee();
            current = current.next;
        }
        current.next = head;
    }

    //Вызываем соотвествующий лифт, который нам нужен
    @Override
    public void call(int _floor, Post _post) throws CallBusyElevator, AllElevatorIsBusy {
        if (_post == Post.employee) {
            this.free(ElevatorPurpose.employee).call(_floor);
        } else if (_floor % 2 == 0) {
            this.free(ElevatorPurpose.even).call(_floor);
        } else {
            this.free(ElevatorPurpose.odd).call(_floor);
        }
    }

    //если цикл дошел до конца и всех проверил, кидаем ошибку что все лифты заняты
    @Override
    public Elevator free(ElevatorPurpose _purpose) throws AllElevatorIsBusy {
        for (int i=0; i<countElevator; i++){
            if (current.getStatus()==Status.free && current.getPurpose() == _purpose) return current;
            current = current.next;
        }
        throw new AllElevatorIsBusy("All Elevator is busy!");
    }

    //двигаем все лифты
    public void move() {
        for (int i = 0; i < countElevator; i++) {
            current.move();
            current = current.next;
        }
    }
}
