package br.ufrj.nce.recureco.distributedindex.common.stopword;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sauloandrade
 * Date: 7/21/13
 * Time: 3:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class StopWordVerifierTest {

    private StopWordVerifier stopWordVerifier;

    @Before
    public void setup() {
        this.stopWordVerifier = new StopWordVerifier();
    }

    @Test
    public void isStopWordSucessTest() {

        Assert.assertTrue(stopWordVerifier.isStopWord("a"));
        Assert.assertTrue(stopWordVerifier.isStopWord("an"));
        Assert.assertTrue(stopWordVerifier.isStopWord("and"));
        Assert.assertTrue(stopWordVerifier.isStopWord("are"));
        Assert.assertTrue(stopWordVerifier.isStopWord("as"));
        Assert.assertTrue(stopWordVerifier.isStopWord("at"));
        Assert.assertTrue(stopWordVerifier.isStopWord("be"));
        Assert.assertTrue(stopWordVerifier.isStopWord("by"));
        Assert.assertTrue(stopWordVerifier.isStopWord("for"));
        Assert.assertTrue(stopWordVerifier.isStopWord("from"));
        Assert.assertTrue(stopWordVerifier.isStopWord("has"));
        Assert.assertTrue(stopWordVerifier.isStopWord("he"));
        Assert.assertTrue(stopWordVerifier.isStopWord("in"));
        Assert.assertTrue(stopWordVerifier.isStopWord("is"));
        Assert.assertTrue(stopWordVerifier.isStopWord("it"));
        Assert.assertTrue(stopWordVerifier.isStopWord("its"));
        Assert.assertTrue(stopWordVerifier.isStopWord("of"));
        Assert.assertTrue(stopWordVerifier.isStopWord("on"));
        Assert.assertTrue(stopWordVerifier.isStopWord("that"));
        Assert.assertTrue(stopWordVerifier.isStopWord("the"));
        Assert.assertTrue(stopWordVerifier.isStopWord("to"));
        Assert.assertTrue(stopWordVerifier.isStopWord("was"));
        Assert.assertTrue(stopWordVerifier.isStopWord("were"));
        Assert.assertTrue(stopWordVerifier.isStopWord("will"));
        Assert.assertTrue(stopWordVerifier.isStopWord("with"));

    }

    @Test
    public void isNotStopWordSucessTest() {

        Assert.assertFalse(stopWordVerifier.isStopWord("anything"));
        Assert.assertFalse(stopWordVerifier.isStopWord("different"));
        Assert.assertFalse(stopWordVerifier.isStopWord("considered"));
        Assert.assertFalse(stopWordVerifier.isStopWord("stop"));
        Assert.assertFalse(stopWordVerifier.isStopWord("word"));

    }

}
