package chap04.item20;

public class Interface {

    interface Singer {
        void sing(String title);
    }

    interface SongWriter {
        void compose(String title, String lyrics);
    }

    interface SingerSongWriter extends Singer, SongWriter {
        void strum();
        void actSensitive();
    }
}

/*
클래스를 통해 이러한 구조를 만들면 고도비만 계층구조가 만들어진다
특히나 확장될수록 추가해줘야하는 클래스가 어마어마해진당
 */