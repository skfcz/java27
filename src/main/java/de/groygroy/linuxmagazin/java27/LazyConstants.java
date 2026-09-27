final LazyConstant<BeiBedarf> LAZY = LazyConstant.of(BeiBedarf::new);

void main() throws Exception {

    for ( int i = 1; i < 5; i++ ) {
        IO.println("Iteration " + i + ", " + FORMATTER.format(LocalDateTime.now()));

        if ( i >=3 ) {
            IO.println("   Inhalt von LAZY: '" + LAZY.get() + "'");
        }
        Thread.currentThread().sleep(2500);
    }
}

public class BeiBedarf {
    private LocalDateTime instant;

    public BeiBedarf() {
        instant = LocalDateTime.now();
        IO.println("   BeiBedarf() um "+FORMATTER.format(instant));
    }

    @Override
    public String toString() {
        return "BeiBedarf " + FORMATTER.format(instant) ;
    }
}

final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss");