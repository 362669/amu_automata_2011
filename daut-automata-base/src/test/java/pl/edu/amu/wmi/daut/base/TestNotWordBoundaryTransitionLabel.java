package pl.edu.amu.wmi.daut.base;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * Test klasy NotWordBoundaryTranistionLabel.
 */
public class TestNotWordBoundaryTransitionLabel extends TestCase {

    /**
     * Proste testy metod klasy NotWordBoundaryTransitionLabel.
     */
    public final void testFirst() {

        AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();

        spec.addTransition(q0a, q1a, new NotWordBoundaryTransitionLabel());
        String str = new String("NotWordBoundary");

        List<OutgoingTransition> lista = new ArrayList<OutgoingTransition>();
        lista.addAll(spec.allOutgoingTransitions(q0a));
        assertEquals(str, lista.get(0).getTransitionLabel().toString());

        assertFalse(lista.get(0).getTransitionLabel().isEmpty());

        assertFalse(lista.get(0).getTransitionLabel().canAcceptCharacter('c'));
    }

    /**
     * Test metody doCheckContext.
     */
    public final void testDoCheckContext() {

        AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();

        spec.addTransition(q0a, q1a, new NotWordBoundaryTransitionLabel());

        List<OutgoingTransition> lista = new ArrayList<OutgoingTransition>();
        lista.addAll(spec.allOutgoingTransitions(q0a));

        boolean thrown = false;
        try {
            lista.get(0).getTransitionLabel().doCheckContext("napis", -1);
        } catch(PositionOutOfStringBordersException e) {
            thrown = true;
        }
        assertTrue(thrown);


        try {
            lista.get(0).getTransitionLabel().doCheckContext("napis", 6);
        } catch(PositionOutOfStringBordersException e) {
            thrown = false;
        }

        assertFalse(lista.get(0).getTransitionLabel().doCheckContext("napis", 0));
        assertFalse(lista.get(0).getTransitionLabel().doCheckContext("napis", 4));
        assertTrue(lista.get(0).getTransitionLabel().doCheckContext("napis", 2));

    }
}
