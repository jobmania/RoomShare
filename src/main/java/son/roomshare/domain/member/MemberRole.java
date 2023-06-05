package son.roomshare.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {

    ADMIN("ROLE_ADMIN"),
    HOME_OWNER("ROLE_HOME-OWNER"),
    HOME_USER("ROLE_HOME-USER");


    private String value;

    MemberRole(String value){
        this.value = value;
    }

}
