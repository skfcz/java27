import org.openjdk.jol.info.ClassLayout;

class Einfach {
    boolean b0;
    int i0;
    boolean b1;
}
class Wrapper {
    List<Long> ints;
}

void main() {

    IO.print(ClassLayout.parseClass(Einfach.class).toPrintable());
    IO.print(ClassLayout.parseClass(Integer.class).toPrintable());
    IO.print(ClassLayout.parseClass(LocalDate.class).toPrintable());
    IO.print(ClassLayout.parseClass(Wrapper.class).toPrintable());
    IO.print(ClassLayout.parseClass(Long.class).toPrintable());
    IO.print(ClassLayout.parseClass(Double.class).toPrintable());
}


