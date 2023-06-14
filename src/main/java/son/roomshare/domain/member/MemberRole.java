package son.roomshare.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {

    관리자("ROLE_ADMIN"),
    외지인("ROLE_OUTSIDER"),
    현지인("ROLE_LOCAL");


    private String value;

    MemberRole(String value){
        this.value = value;
    }

}
