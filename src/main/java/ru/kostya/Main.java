package ru.kostya;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ElevatorList elevators = new ElevatorList(3, 3, 1);
        //работаем 5000 секунд, каждые пол секунды лифт двигается на этаж
        for (int i = 0; i<10000; i++){
            try {
                elevators.move();
                if(i==0) {
                    elevators.call(50, Post.common);
                    elevators.call(60, Post.common);
                    elevators.call(70, Post.common);
                    elevators.call(55, Post.common);
                    elevators.call(65, Post.common);
                    elevators.call(75, Post.common);
                    elevators.call(10, Post.employee);
                }
                if(i/2==30){
                    elevators.call(2, Post.common);
                    elevators.call(4, Post.common);
                    elevators.call(6, Post.common);
                    elevators.call(1, Post.common);
                    elevators.call(3, Post.common);
                    elevators.call(5, Post.common);
                    elevators.call(20, Post.employee);
                }

            } catch (CallBusyElevator | AllElevatorIsBusy e){
                System.out.println(e.getMessage());
            }

            if(i%2==0) System.out.println(i/2);
            Thread.sleep(500);

        }
    }
}