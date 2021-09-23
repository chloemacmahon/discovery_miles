package za.ac.nwu.ac.domain.exception;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(Long id) {
        super("Could not find member with id "+ id);
    }
}
