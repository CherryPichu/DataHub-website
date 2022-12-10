<!DOCTYPE html>

<html lang="en">
<%--Jquery 라이브러리--%>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--부트스트랩 적용!--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<meta name="viewport">

<link href="/css/index.css" rel="stylesheet" type="text/css"/>


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>지역 기반 데이터 클라우드 서비스</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.8.0/dist/leaflet.css"
          integrity="sha512-hoalWLoI8r4UszCkZ5kL8vayOGVae1oxXe/2A4AO6J9+580uKHDO3JdHb7NzwwzK5xr/Fs0W40kiNHxM9vyTtQ=="
          crossorigin=""/>

    <script src="https://unpkg.com/leaflet@1.8.0/dist/leaflet.js"
            integrity="sha512-BB3hKbKWOc9Ez/TAwyWxNXeoV9c1v6FIeYiBieIWkpLjauysF18NzgR1MBNBXf8/KABdlkX68nAhlwcDFLGPCQ=="
            crossorigin=""></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/monet/0.9.3-485/monet.min.js"></script>
    <script type="text/javascript">
        function upload(location) {
            let uploadDiv = document.querySelector(".upload");
            if (uploadDiv != null) uploadDiv.remove();
            uploadDiv = document.createElement("div");
            uploadDiv.className = "upload";

            uploadDiv.innerHTML = `
                <h1>업로드</h1>
                <hr>
                <b style="padding-right: 7px">필드명</b><input type="text" style="width: 300px;">&nbsp;

                <br><br>
                <b>데이터 상세 설명</b>
                <div style="height: 7px"></div>
                <textarea name="detail" style="width: calc(80% - 7px); resize: none;" rows="4"></textarea>
                <div style="height: 7px"></div>
                <div style="float: left; width:100%;">


                </div>
                <table style="width: 100%;">
                    <tr>
                        <td> lat : <input type="text" id="LatInfo" class="form-control" style="width: calc(80% - 7px); " disabled></td>
                        <td> lng : <input type="text" id="LngInfo" class="form-control" style="width: calc(80% - 7px);"  disabled></td>
                    </tr>
                </table>
                <div style="height: 7px"></div>
                DB에 데이터 보내는 주소 : <input type="text" id="tokenInfo" class="form-control" style="width: calc(80% - 7px); " disabled>
                <div style="height: 7px"></div>
                <span class="buttons" style="float: left;">
                        <button type="button" class="btn btn-dark" id="BtnLocationCancel" style="float: left">취소</button>
                        <button type="button" class="btn btn-secondary" id="BtnPostLocationData" onclick="">등록</button>
                </span>`;
            document.body.appendChild(uploadDiv);
        }

        function cancelUpload() {
            document.querySelector(".upload").remove();
        }

        window.onload = () => {
            const map = L.map('map').setView([37.8835, 127.73], 16); // 기초 시작 지점

            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: ''
            }).addTo(map)
            map.attributionControl.setPrefix('한림대학교 정보과학대학')


            map.on('contextmenu', function (e) {
                let location = e.latlng
                console.log(location)

                with (location) {
                    let popup = L.popup()
                        .setLatLng(e.latlng)
                        .setContent(`
                            <div class="upload-popup">
                                <i class="fa-solid fa-compass"></i>&nbsp;&nbsp;
                                위도: `+ lat.toFixed(6) + `, 경도: `+ lng.toFixed(6)+ `,<br>
                                <i class="fa-solid fa-table"></i>&nbsp;&nbsp;
                                <a href="javascript:upload(location);">데이터를 업로드하려면 클릭하세요.</a>
                            </div>
                        `)
                        .openOn(map);
                }
            });
            // upload(null)

        }
    </script>

    <style>

    </style>
</head>

<body id="#body">

<%--맵 구성--%>
<div id="map">


</div>

<div class="account">
    <span style="float:left;" class="name">
        <i class="fa-solid fa-user-graduate"></i>아이디 : <span id="nickname">null</span>
    </span>
    <span style="float:right;">
        <i class="fa-solid fa-landmark"></i>춘천시&nbsp;&nbsp;
<%--        <i class="fa-solid fa-table"></i>5건&nbsp;--%>
    </span>
</div>


<%--로그인페이지--%>
<div class="data" id="mainpage" style=" float: left;"> </div>






</body>

</html>

<script src="/js/index.js"> </script>
<script>


    /**
     * 데이터
     */
    const PostMarkLocation = () => {
        const SigUpId = $("#ㅇㅁㅅ").val()
        const SigUpPw = $("#SigUpPw").val()
        const SigUpNickname = $("#SigUpNickname").val() // 성명
        const SigUpEmailFront = $("#SigUpEmail-front").val() // uskawjdu@
        const SigUpEmailBack = $("#SigUpEmail-back").val() // @gamil.com
        const inputGroupText = $('input[name=sex]:checked').val() // sex

        const user_name = $("#SigUpCNAME").val() // 별칭, 별명


        const params = {
            data : Sig,
            detail : SigUpNickname, // 별칭
            fieldname : SigUpNickname,
            fieldId : fieldId,
            lat : SigUpEmailFront + "@"+SigUpEmailBack ,
            lng: SigUpPw,
            // token : inputGroupText,
            // user_no : user_name
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
     * 메인페이지 화면 구성
     * /auth/getuserJson 도메인에서 유저세션 정보로 유저 정보를 받아옴.
     */
    const pageReload = () =>{
        $.ajax({
            type: "GET",
            dataType: "json",
            timeout: 500,
            url: "/auth/getuserJson",
            data: $(this).serialize(),
            success: (res) => {
                console.log(res)
                $('#nickname').text(res.user_name); // 닉네임 구성
                if(res.user_name == "null"){
                    $("#mainpage").html(loginpage) // 로그인 안된 사용자.
                }else{
                    $("#mainpage").html(mainpage)
                }

                console.log(res.token)
                $('#tokenBox').html(res.token)

            },
            error: (res) => {

                console.error("ㅠㅠ 실패")

            }
        })

    }
    pageReload();


    /**
     * 메인페이지 구현
     */
    const mainpage = `
        <button type="button" class="btn btn-dark" id="BtnLogin" style="float: right", onclick="logout()">로그아웃</button>
        <br>
        <br>
        토큰 번호
        <div class="text-nowrap bg-light border" id="tokenBox" style="width: 30rem;">
          토큰 번호
        </div>

`
    const loginpage = `

    <div class="title" style="font-weight: bold;">로그인</div>
    <table>

        <tr>
            <th>
                <b style="padding-right: 4px">아이디 : </b>
            </th>
            <th>
                <input type="id" class="form-control" id="inputId">
            </th>
        </tr>
        <tr>
            <th>
            <b style="padding-right: 4px">패스워드 : </b>
            </th>
            <th>
                <input type="password" class="form-control" id="inputPassword">
            </th>
        </tr>
    </table>

    <div style="width: 283px;">
        <button type="button" class="btn btn-dark" id="BtnLogin" style="float: right", onclick="loginPost()">로그인</button>
        <button type="button" class="btn btn-secondary" onclick="dragSignUpPage()">회원가입</button>
    </div>
    `

// 이제 데이터 입력 및 시각화 작업을 하자.


</script>




