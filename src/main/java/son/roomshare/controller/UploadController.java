package son.roomshare.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import son.roomshare.domain.member.MemberDetailsImpl;
import son.roomshare.domain.uploadfile.FileStore;
import son.roomshare.domain.uploadfile.UploadFile;
import son.roomshare.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    private final FileStore fileStore;
    private final MemberService memberService;

    @PostMapping("")
    public String saveFile(@RequestParam MultipartFile file,
                           HttpServletRequest request,
                           @AuthenticationPrincipal MemberDetailsImpl memberDetails
                           ) throws IOException {

        String savedToDbName = fileStore.storeFile(file);

      

        log.info("memberDetails ={}",memberDetails.getMember().getEmail());
        memberService.updateImageFile(memberDetails.getMember(),savedToDbName);


        log.info("file={}",file);
        return "redirect:/";
    }
}
