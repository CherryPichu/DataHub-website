



// $("#BtnLogin")
const loginPost = () =>{
    const id = $("#inputId").val()
    const pw = $("#inputPassword").val()
    const params = {
        id: id,
        password: pw,
    }

    $.ajax({
        type: "POST",
        timeout: 500,
        url: "/auth/login",
        data: params,
        // data: params,
        success: (res) => { // 로그인 성공
            pageReload() // 페이지 리로드

        },
        error: (res) => {
            alert("로그인 실패! 아이디 또는 패스워드 확인")
            console.error("ㅠㅠ 실패")

        }
    })
}






/**
 * 회원가입 페이지 열기
 */
const dragSignUpPage = () => {
    if( document.querySelector(".signup") == null){
        let body = document.querySelector("body");
        body.innerHTML += SignUpForm
        console.log("회원가입 페이지 열기")
    }
}

/**
 * 회원가입 페이지 닫기
 */
const cancelSignUpPage = () => {
    if( document.querySelector(".signup") != null){
        let signupPage = document.querySelector(".signup");
        signupPage.remove()
    }
}




/**
 * 회원가입 요청 이벤트
 */
const PostSignUp = () => {
    const SigUpId = $("#SigUpId").val()
    const SigUpPw = $("#SigUpPw").val()
    const SigUpNickname = $("#SigUpNickname").val() // 성명
    const SigUpEmailFront = $("#SigUpEmail-front").val() // uskawjdu@
    const SigUpEmailBack = $("#SigUpEmail-back").val() // @gamil.com
    const inputGroupText = $('input[name=sex]:checked').val() // sex

    const user_name = $("#SigUpCNAME").val() // 별칭, 별명


    const params = {
        AuthNickname: SigUpId,
        MemberNickname : SigUpNickname, // 별칭
        // password: SigUpNickname,
        email: SigUpEmailFront + "@"+SigUpEmailBack ,
        password: SigUpPw,
        sex : inputGroupText,
        user_name : user_name
    }

    $.ajax({
        type: "POST",
        timeout: 500,
        url: "/auth/signUp",
        data: params,
        success: (res) => {
            alert("회원가입 성공!")
            cancelSignUpPage()
        },
        error: (res) => {
            alert("회원가입 실패..")
        }
    })

}

/**
 * 아이디 중복 검사 버튼 이벤트
 */
const CheckDuplicatedId = () => {

    const params = {
        id: $("#SigUpId").val()
    }


    $.ajax({
        type: "POST",
        timeout: 500,
        url: "/auth/CheckedDuplicatedId",
        data: params,

        success: (res) => {
            if(res.toString() != "yes")
                alert("중복이 있는 아이디 입니다. 다시입력해주세요.")
            else
                alert("중복이 없는 아이디 입니다.")

        },
        error: (res) => {
            alert("에러")
            
        }
    })

}

const SignUpForm =
    `
<%--회원가입 페이지 --%>
<div class="signup" style="border-radius: 15px;">
    <div>
        <h1>회원가입</h1>
        <hr>
        <table>
            <tr>
                <td>
                    <b>아이디 </b>
                </td>
                <td>
                    <input type="text" id="SigUpId" class="form-control" >
                </td>
                <td>
                    <button style="margin-left: 20px; width:100px;"  type="button" class="btn btn-dark" id="CheckDuplicatedId"
                    onClick="CheckDuplicatedId()">중복검사</button>
                </td>
            </tr>

            <tr>
                <td>
                    <b>비밀번호</b>
                </td>
                <td>
                    <input type="password" id="SigUpPw" class="form-control" >
                </td>
            </tr>

            <tr>
                <td >
                    <b>이름</b>
                </td>
                <td>
                    <input type="text" id="SigUpNickname" class="form-control" >
                </td>
            </tr>
            <tr>
               <td >
                    <b>별칭 (닉네임) </b>
                </td>
                <td>
                    <input type="text" id="SigUpCNAME" class="form-control" >
                </td>
            </tr>

            <tr>
                <td >
                    <b>이메일 </b>
                </td>
                <td>
                    <div class="input-group mb-3">
                        <input type="text" class="SigUpEmail-front" placeholder="Username" aria-label="Username">
                        <span class="input-group-text">@</span>
                        <input type="text" class="SigUpEmail-back" placeholder="Server" aria-label="Server">
                    </div>
                </td>
            </tr>

            <tr>
                <td >
                    <b>성별</b>
                </td>
                <td>
                    <div class="input-group">
                        <div class="input-group-text">
                            <b>남자</b><input class="form-check-input mt-0" type="radio" id="SignUpMale" value=0  name="sex" checked="" >&nbsp&nbsp&nbsp
                            <b>여자</b><input class="form-check-input mt-0" type="radio" id="SignUpFemale" value=1  name="sex">
                        </div>
                    </div>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <button type="button"  class="btn btn-primary" id="SignUpSubmit" style="width:100px" onclick="PostSignUp()">회원가입</button>
                </td>

                <td>
                    <button type="button" style="margin-left: 20px; width:80px;"  class="btn btn-dark" id="SignUpCancel"
                    onClick="cancelSignUpPage()">취소</button>
                </td>
            </tr>

        </table>
    </div>
</div>
        `;
