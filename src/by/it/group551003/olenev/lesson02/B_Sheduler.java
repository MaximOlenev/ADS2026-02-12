package by.it.group551003.olenev.lesson02;

import java.util.ArrayList;
import java.util.List;

/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {


        List<Event> result = new ArrayList<>();

        List<Event> validEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.start >= from && event.stop <= to) {
                validEvents.add(event);
            }
        }

        for (int i = 0; i < validEvents.size() - 1; i++) {
            for (int j = i + 1; j < validEvents.size(); j++) {
                if (validEvents.get(i).stop > validEvents.get(j).stop) {
                    Event temp = validEvents.get(i);
                    validEvents.set(i, validEvents.get(j));
                    validEvents.set(j, temp);
                }
            }
        }

        int lastEndTime = from;

        for (Event event : validEvents) {

            if (event.start >= lastEndTime) {
                result.add(event);
                lastEndTime = event.stop;
            }
        }

        return result;
    }


    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }
}