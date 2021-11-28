package chap04.item24;

import lombok.NoArgsConstructor;

class StaticMemberClass {

    private int num;

    public StaticMemberClass(int num) {
        this.num = num;
    }

    static class StaticMember {
        static void panda() {
            int num = 5;
            System.out.println("num = " + num);
        }
    }

    @NoArgsConstructor
    class NonStaticMember {
        void bear() {
            System.out.println("num = " + num);
        }
    }
}
