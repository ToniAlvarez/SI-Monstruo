package monstruo;

/**
 * Interfícia de comunicació del patró per esdeveniments
 * <p>
 * No és òptim però és molt visual acadèmicament per entendre el funcionament,
 * emprar un String com entitat de comunicació.
 *
 * @author antoniAlvarez
 * @author juanjoTorres
 */
public interface EventsListener {
    void notify(String message);
}
