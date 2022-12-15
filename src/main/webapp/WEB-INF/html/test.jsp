<!DOCTYPE html>

<html lang="en">
<%--Jquery 라이브러리--%>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="https://cdn.plot.ly/plotly-2.16.1.min.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--부트스트랩 적용!--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<meta name="viewport">

<link href="/css/index.css" rel="stylesheet" type="text/css"/>

<div class="ViewGraphPage" >
    <div style="height: 10px;"></div>
    <h2>데이터 그래프 확인 <button type="button" class="btn btn-dark" id="" style="float: right;" onclick="">X</button></h2>

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
    <button type="button" class="btn btn-dark" style="float: left; margin-right: 100px;" onclick="">데이터 다운로드</button>
    <button type="button" class="btn btn-dark" style="float: right;" onclick="">마커 데이터 삭제</button>

</div>


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

<script>

    var token = "$2a$15$5jPWxmdoAvhCQGyvJv8sbu"
    var field = "데이터"
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

                console.log(res)
            },
            error: (res) => {

                console.error("ㅠㅠ 실패")
            }
        })


    }
    getDatas(token, field)

</script>