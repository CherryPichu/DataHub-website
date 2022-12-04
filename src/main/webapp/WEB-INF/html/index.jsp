<!DOCTYPE html>
<html lang="en">
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
                <b style="padding-right: 7px">데이터명</b><input type="text" style="width: 500px;">&nbsp;
                <b style="padding-right: 7px">분류</b>
                <select id="category" style="width: 72px;height: 22px;">
                    <option value="사회">사회</option>
                    <option value="자연과학">자연과학</option>
                </select>
                <br><br>
                <b>데이터 설명</b>
                <div style="height: 7px"></div>
                <textarea name="Text1" style="width: calc(100% - 7px); resize: none;" rows="18"></textarea>
                <div style="height: 7px"></div>
                <input type="file">
                <span class="buttons" style="float: right">
                    <input type="button" style="width: 65px;" value="Upload"><span style="width: 2px"></span>
                    <input type="button" style="width: 65px;" value="Cancel" onclick="cancelUpload()">
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

                console.log(e.latlng) // 좌표
                // ex) {lat: 37.88501530712817, lng: 127.72404670715333}



                with (location) {
                    let popup = L.popup()
                        .setLatLng(e.latlng)
                        .setContent(`
                            <div class="upload-popup">
                                <i class="fa-solid fa-compass"></i>&nbsp;&nbsp;
                                위도: ${lat.toFixed(6)}, 경도: ${lng.toFixed(6)}<br>
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

<body>

<%--맵 구성--%>
<div id="map">


</div>


"""
<div class="account">
    <span style="float:left;" class="name">
        <i class="fa-solid fa-user-graduate"></i>아이디 : <span id="nickname">null</span>
    </span>
    <span style="float:right;">
        <i class="fa-solid fa-landmark"></i>춘천시&nbsp;&nbsp;
<%--        <i class="fa-solid fa-table"></i>5건&nbsp;--%>
    </span>
</div>



<div class="data" id="mainpage" style=" float: left;"> </div>


    <div>
        <h1>회원가입</h1>
        <hr>
        <table>

            <input type="text" id="SigUpnickname">
            <br><br>
            <b>데이터 설명</b>
            <div style="height: 7px"></div>
            <input>
            <span class="buttons" style="float: right">
                            <input type="button" style="width: 65px;" value="Upload"><span style="width: 2px"></span>
                            <input type="button" style="width: 65px;" value="Cancel" onclick="cancelUpload()">
                        </span>
        </table>
    </div>


</body>

</html>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="/js/index.js"> </script>
<script>

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
            success: (res) => {
                pageReload()
            },
            error: (res) => {
                alert("로그인 실패! 아이디 또는 패스워드 확인")
                console.error("ㅠㅠ 실패")

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
        <button type="button" class="btn btn-secondary" >회원가입</button>
    </div>
    `



    const logout = () => {
        $.ajax({
            type: "GET",
            timeout: 500,
            url: "/auth/logout",
            // data: params,
            success: (res) => {
                pageReload()
            },
            error: (res) => {

                console.error("ㅠㅠ 실패")
            }
        })
    }

    // const PostSignUp = () => {
    //     $.ajax({
    //         type: "GET",
    //         timeout: 500,
    //         url: "/auth/logout",
    //         // data: params,
    //         success: (res) => {
    //             pageReload()
    //         },
    //         error: (res) => {
    //
    //             console.error("ㅠㅠ 실패")
    //         }
    //     })
    // }



    const SignUpForm =
        `
                <h1>회원가입</h1>
                <hr>
                <table>

                 <input type="text" id="SigUpnickname">
                <br><br>
                <b>데이터 설명</b>
                <div style="height: 7px"></div>
                <input>
                <span class="buttons" style="float: right">
                    <input type="button" style="width: 65px;" value="Upload"><span style="width: 2px"></span>
                    <input type="button" style="width: 65px;" value="Cancel" onclick="cancelUpload()">
                </span>
        `;

</script>




