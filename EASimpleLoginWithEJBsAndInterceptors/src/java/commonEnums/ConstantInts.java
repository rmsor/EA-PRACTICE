/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonEnums;

/**
 *
 * @author Admin
 */
public enum ConstantInts {
    //these are common constant integers
    PasswordLength(6),
    DupEmail(1),
    NoUser(2),
    RegSuccess(3),
    RegFail(4),
    LoginApplicantSuccess(5),
    LoginAdminSuccess(6),
    LoginFail(7), 
    BadPassword(8),
    MaxLoginAttempts(3);
    
    private final int value;

    public int getValue() {
        return value;
    }
    ConstantInts(int v){
        value = v;
    }
    
}
