package com.hemanth.entities;

public enum SessionDay {
    MONDAY {
        @Override
        public String toString() {
            return "Monday";
        }
    },
    WEDNESDAY {
        @Override
        public String toString() {
            return "Wednesday";
        }
    },
    FRIDAY {
        @Override
        public String toString() {
            return "Friday";
        }
    },
    SATURDAY {
        @Override
        public String toString() {
            return "Saturday";
        }
    }
}
