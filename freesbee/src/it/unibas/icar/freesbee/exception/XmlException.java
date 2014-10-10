/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.unibas.icar.freesbee.exception;

public class XmlException extends Exception {

    public XmlException() {
    }

    public XmlException(String msg) {
        super(msg);
    }

    public XmlException(Exception e) {
        super(e);
    }

}
