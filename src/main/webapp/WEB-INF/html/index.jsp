<!DOCTYPE html>

<html lang="en">
<%--Jquery 라이브러리--%>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<%--plotly 라이브러리--%>
<script src="https://cdn.plot.ly/plotly-2.16.1.min.js"></script>

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
        /**
         * locationData.leg
         * {lat: 37.88830069256622, lng: 127.72715806961061}
         *
         * TOKEN
         * 토큰 번호
         *
         * isLogin
         * 현재 로그인되었나?
         * bool
         *
         */
        let locationData;
        let TOKEN;
        let isLogin;

        function upload() {
            // 로그인 안됐으면 return 하는 문장 추가하기.


            /**
             * 기존에 페이지가 있으면 삭제하고 새로운 페이지를 만듬.
             */
            let uploadDiv = document.querySelector(".uploadPage");
            if (uploadDiv != null) uploadDiv.remove();
            uploadDiv = document.createElement("div");
            uploadDiv.className = "uploadPage";
            uploadDiv.innerHTML = `
                <h1>업로드</h1>
                <hr>
                <b style="padding-right: 7px" >필드명</b><input type="text" id="fieldname" style="width: 300px;">&nbsp;

                <br><br>
                <b>데이터 상세 설명</b>
                <div style="height: 7px"></div>
                <textarea name="detail" style="width: calc(80% - 7px); resize: none;" id="detail" rows="4"></textarea>
                <div style="height: 7px"></div>
                <div style="float: left; width:100%;">


                </div>
                <table style="width: 100%;">
                    <tr>
                        <td> lat : <input type="text" id="LatInfo" class="form-control" style="width: calc(80% - 7px); " disabled></td>
                        <td> lng : <input type="text" id="LngInfo" class="form-control" style="width: calc(80% - 7px);" disabled></td>
                    </tr>
                </table>
                <div style="height: 7px"></div>
                DB에 데이터 보내는 주소 : <input type="text" id="tokenInfo" class="form-control" style="width: calc(80% - 7px); "disabled>
                <div style="height: 7px"></div>
                <span class="buttons" style="float: left; width:80%">
                        <button type="button" class="btn btn-dark" id="BtnLocationCancel" style="float: left; margin-right: 100px;" onclick="cancelUploadLocation()">취소</button>
                        <button type="button" class="btn btn-secondary" style="float: right;" id="BtnPostLocationData" onclick="PostMarkLocation()">등록</button>
                </span>`;
            document.body.appendChild(uploadDiv);

            $("#LatInfo").val( locationData.lat )
            $("#LngInfo").val( locationData.lng )
            $("#tokenInfo").val( TOKEN )
        }

        var map; // 전역변수 선언!!
        var mapMarkers = []; // 마커 객체들
        window.onload = () => {
            map = L.map('map').setView([37.8835, 127.73], 15); // 기초 시작 지점


            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: ''
            }).addTo(map)
            map.attributionControl.setPrefix('한림대학교 정보과학대학')


            map.on('contextmenu', function (e) {
                let location = e.latlng
                locationData = e.latlng

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
            pageReload();
            reLoadMakeMarker()

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
     * 데이터 Location 서버에 등록
     */
    const PostMarkLocation = () => {
        const fieldNmae = $("#fieldname").val() // 필드명
        const detail = $("#detail").val() // 데이터 상세 설명
        const Lat = $("#LatInfo").val() // 위도
        const Lng= $("#LngInfo").val() // 경도
        const token = TOKEN // 경도

        if("fieldName" == ""){
            alert("fieldName 을 입력하세요.")
            return;
        }

        const user_name = $("#SigUpCNAME").val() // 별칭, 별명


        const params = {
            fieldname: fieldNmae,
            detail : detail,
            lat : Lat ,
            lng: Lng,
            token : token
        }

        $.ajax({
            type: "POST",
            timeout: 500,
            url: "/data/api/postLocationData",
            data: params,
            success: (res) => {
                reLoadMakeMarker()
                alert("해당 자표 데이터 필드 생성 완료!")
                document.querySelector(".uploadPage").remove()
            },
            error: (res) => {
                alert("데이터 생성 에러.")

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
                // console.log(res)
                if(res.token != ""){
                    $('#nickname').text(res.user_name); // 닉네임 구성
                    TOKEN = res.token // 토큰 데이터 저장
                    $("#mainpage").html(mainpage) // 로그인이 된 사용자.
                    $("#mainpagetoken").text(res.token) // token 넣기

                }else{
                    $("#mainpage").html(loginpage) // 로그인 안된 사용자.
                }
                reLoadMakeMarker()
            },
            error: (res) => {
                console.error("ㅠㅠ 실패")
            }
        })


    }

    // 로그아웃 요청
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

    /**
     * 메인페이지 구현
     */
    const mainpage = `
        <button type="button" class="btn btn-dark" id="BtnLogin" style="float: right", onclick="logout()">로그아웃</button>
        <br>
        <br>
        토큰 번호
        <div class="text-nowrap bg-light border" id="tokenBox" style="width: 30rem;">
          토큰 번호 : <span id="mainpagetoken"> </span>
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
        <button type="button" class="btn btn-dark" id="BtnLogin" style="float: right" onclick="loginPost()">로그인</button>
        <button type="button" class="btn btn-secondary" onclick="dragSignUpPage()">회원가입</button>
    </div>
    `


    /**
     * 데이터 위치 등록 버튼.
     */
    const cancelUploadLocation = () => {
        if( document.querySelector(".uploadPage") != null){
            let signupPage = document.querySelector(".uploadPage");
            signupPage.remove()
        }
    }


    /**
     * 지도에 marker 를 생성함.
     */
    const reLoadMakeMarker = () => {
        $.ajax({
            type: "GET",
            dataType: "json",
            timeout: 500,
            url: "/data/api/getLocationData",
            data: $(this).serialize(),
            success: (res) => {


                /**
                 * 마커 전부 삭제
                 * 참고 : https://stackoverflow.com/questions/28636723/how-to-clear-leaflet-map-of-all-markers-and-layers-before-adding-new-ones
                 */
                $('#nickname').text("null"); // 닉네임 구성
                for(var i = 0; i < this.mapMarkers.length; i++){ // 맵 마커 전부 삭제
                    map.removeLayer(this.mapMarkers[i]);
                }
                this.mapMarkers = [] // 마커 기록 삭제
                // console.log( mapMarkers)


                if(res == null){ // 결과 데이터가 없다면.
                    return;
                }

                /**
                 * 받아온 마커 정보를 토대로 지도에 마커 생성.
                 * 참고 : https://stackoverflow.com/questions/16927793/marker-in-leaflet-click-event
                 */
                for(let data of res){
                    // console.log(data)

                    let marker = L.marker( [data.lat, data.lng])
                    mapMarkers.push(marker) // 마커 데이터 추가.
                    marker.on({"click" : (event) => {
                        let body = document.querySelector("body");

                        let  viewGraphPage = document.querySelector(".ViewGraphPagee");
                        if ( viewGraphPage != null)  viewGraphPage.remove();
                        viewGraphPage = document.createElement("div");
                        viewGraphPage.className = "ViewGraphPage";
                        viewGraphPage.innerHTML = ViewGraphPage;
                        document.body.appendChild(viewGraphPage);
                        $("#LatInfo").val( data.lat )
                        $("#LngInfo").val( data.lng )
                        $("#tokenInfo").val( "http://localhost:8088/data/api/PostData?data={데이터}&fieldname="+ data.fieldname +"&token=" + TOKEN )
                        $("#fieldname").text( data.fieldname )
                        $("#invisiableToken").val( TOKEN ) // 메타 데이터, 삭제에 활용
                        getDatas(TOKEN,  data.fieldname)

                    }}).addTo(map);

                }
                console.log("reLoadMakeMarker 실행!")
            },
            error: (res) => {
                console.error("ㅠㅠ 실패")
            }
        })

    }


</script>

<script>
const ViewGraphPage = `
<div class="ViewGraphPage" >
    <div style="height: 10px;"></div>
    <h2>필드명 :  <strong><span id="fieldname"> </span></strong> <button type="button" class="btn btn-dark" id="" style="float: right;" onclick="cancelViewGraphPage()">X</button></h2>
    <input id="invisiableToken" style="display: none;"></input>
    <hr>

    <div style="float: left; width:100%;">


    </div>
    <table style="width: 100%;">
        <tr>
            <td> lat : <input type="text" id="LatInfo" class="form-control" style="width: calc(80% - 7px); " disabled></td>
            <td> lng : <input type="text" id="LngInfo" class="form-control" style="width: calc(80% - 7px);" disabled></td>
        </tr>
    </table>
    <div style="height: 7px"></div>
    DB에 데이터 보내는 주소 : <input type="text" id="tokenInfo" class="form-control" style="width: calc(80% - 7px); "disabled>
    <div style="height: 7px"></div>
    <span class="buttons" style="float: left; width:80%">
                            <button type="button" class="btn btn-dark" id="BtnLocationCancel" style="float: left; margin-right: 100px;" onclick="cancelUploadLocation()">취소</button>
                            <button type="button" class="btn btn-secondary" style="float: right;" id="BtnPostLocationData" onclick="PostMarkLocation()">등록</button>
    </span>

    <div id='myDiv'><!-- Plotly chart will be drawn inside this DIV --></div>
    <div style="height: 10px;"></div>
    <button type="button" class="btn btn-dark" style="float: left; margin-right: 100px;" onclick="getDatalist()">데이터 다운로드</button>
    <button type="button" class="btn btn-dark" style="float: right;" onclick="deleteLocationMark()">마커 데이터 삭제</button>

</div> `

</script>

<style>
    .ViewGraphPage {
        --width: 700px;
        --height: 700px;
        --padding-horizontal: 20px;
        z-index: 300;
        padding-left: var(--padding-horizontal);
        padding-right: var(--padding-horizontal);
        width: var(--width);
        height: var(--height);
        top: calc(50% - var(--height) / 2);
        left: calc(50% - var(--width) / 2 - var(--padding-horizontal));
        background-color: rgba(255, 255, 255, 0.85);
        position: absolute;
        background: blanchedalmond;
    }
    #myDiv {
        width : 100%;
        height: 400px;
    }

</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!-- 필수, SheetJS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
<!--필수, FileSaver savaAs 이용 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
<script>


    /**
     * plot을 그릴 데이터를 가져옴
     *
     */
    const getDatas = (token, field) => {
        const params = {
            token : token,
            fieldname : field
        }

        $.ajax({
            type: "GET",
            timeout: 500,
            url: "/data/api/GetData",
            data: params,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: (res) => {
                data_set = []
                time_set = []
                for(i of res){
                    data_set.push(i.data);
                    time_set.push(i.create_at)
                }

                /**
                 * 그래프 만듬
                 */
                var data = [
                    {
                        x: time_set,
                        y: data_set ,
                        type: 'scatter'
                    }
                ];
                Plotly.newPlot('myDiv', data);

                // console.log(res)
            },
            error: (res) => {

                console.error("ㅠㅠ 실패")
            }
        })
    }


    const cancelViewGraphPage = () => {
        if( document.querySelector(".ViewGraphPage") != null){
            let signupPage = document.querySelector(".ViewGraphPage");
            signupPage.remove()
        }
    }

    const deleteLocationMark2 = (token, field) => {
        const params = {
            token : token,
            fieldname : field
        }

        $.ajax({
            type: "GET",
            timeout: 500,
            url: "/data/api/DeleteData",
            data: params,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: (res) => {
                console.log(res)


                cancelViewGraphPage();
                reLoadMakeMarker();
            },
            error: (res) => {
                console.error("ㅠㅠ 실패")
            }
        })

    }
    const deleteLocationMark = () => {
        if(!confirm("정말 이 마커(Marker)를 삭제하겠습니까?")){
            return;
        }
        deleteLocationMark2( TOKEN, $("#fieldname").text() )
    }

    function json2array(json){
        var result = [];
        var keys = Object.keys(json);
        keys.forEach(function(key){
            result.push(json[key]);
        });
        return result;
    }

    /**
     * JS로 엑셀 파일 쓰기
     * https://eblo.tistory.com/84
     */
    function s2ab(s) {
        var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
        var view = new Uint8Array(buf);  //create uint8array as viewer
        for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
        return buf;
    }

    const getDatalist2 = (token, field) => {
        const params = {
            token : token,
            fieldname : field
        }

        $.ajax({
            type: "GET",
            timeout: 500,
            url: "/data/api/getDatalist",
            data: params,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: (res) => {
                // console.log(res)

                var excelHandler = {
                    getExcelFileName : function(){
                        return 'json-test.xlsx';
                    },
                    getSheetName : function(){
                        return 'Json Test Sheet';
                    },
                    getExcelData : function(){
                        result = []
                        for(var i of res){
                            result.push(json2array(i))
                        }

                        console.log(res)
                        return res;
                    },
                    getWorksheet : function(){
                        return XLSX.utils.json_to_sheet(this.getExcelData());
                    }

                }

                // step 1. workbook 생성
                var wb = XLSX.utils.book_new();

                // step 2. 시트 만들기
                var newWorksheet = excelHandler.getWorksheet();

                // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.
                XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());

                // step 4. 엑셀 파일 만들기
                var wbout = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});

                // step 5. 엑셀 파일 내보내기
                saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), excelHandler.getExcelFileName());
            },
            error: (res) => {
                console.error("ㅠㅠ 실패")
            }
        })

    }
    const getDatalist = () => {

        getDatalist2( TOKEN, $("#fieldname").text() )
    }
</script>

