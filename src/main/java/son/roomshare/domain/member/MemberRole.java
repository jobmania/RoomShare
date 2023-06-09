package son.roomshare.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {

    관리자("ROLE_ADMIN"),
    집주인("ROLE_HOME-OWNER"),
    세입자("ROLE_HOME-USER");


    private String value;

    MemberRole(String value){
        this.value = value;
    }

}
