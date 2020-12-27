package interfaces;

import java.util.List;

public interface Player<F> {

    F makeMove(List<F> fields, boolean side);
}
