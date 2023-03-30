<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta charset="UTF-8">
    <title>채팅</title>
    <style>
        .chating{
            background-color: #FF8C00;
            width: 500px;
            height: 500px;
            overflow: auto;
        }
        .chating p{
            color: #000;
            text-align: left;
        }
        #yourMsg{
            display: none;
        }
    </style>
</head>

<script type="text/javascript">
    var ws;

    function wsOpen(){
        ws = new WebSocket("ws://" + location.host + "/chating");
        wsEvt();
    }

    function wsEvt() {
        ws.onopen = function(data){
            //소켓이 열리면 초기화 세팅하기
        }

        ws.onmessage = function(data) {
            var msg = data.data;
            if(msg != null && msg.trim() != ''){
                $("#chating").append("<p>" + msg + "</p>");
            }
        }

        document.addEventListener("keypress", function(e){
            if(e.keyCode == 13){ //enter press
                send();
            }
        });
    }

    function chatName(){
        var userName = $("#userName").val();
        if(userName == null || userName.trim() == ""){
            alert("사용자 이름을 입력해주세요.");
            $("#userName").focus();
        }else{
            wsOpen();
            $("#yourName").hide();
            $("#yourMsg").show();
        }
    }

    function send() {
        var uN = $("#userName").val();
        var msg = $("#chatting").val();
        ws.send(uN+" : "+msg);
        $('#chatting').val("");
    }
</script>
<body>
    <div id="container" class="container">
        <h1>채팅</h1>
        <h2>Together Chatting</h2>
        <div id="chating" class="chating">
        </div>

        <div id="yourName">
            <table>
                <tr>
                    <th>닉네임</th>
                    <th><input class="form-control form-control-sm" type="text" name="userName" id="userName"></th>
                    <th><button class="btn btn-sm btn-warning" onclick="chatName()" id="startBtn">등록</button></th>
                </tr>
            </table>
        </div>
        <div id="yourMsg">
            <table>
                <tr>
                    <th>메시지</th>
                    <th><input class="form-control form-control-sm" id="chatting" placeholder="보낼 메시지를 입력하세요."></th>
                    <th><button class="btn btn-sm btn-warning" onclick="send()" id="sendBtn">보내기</button></th>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>
    <%@ include file="layout/footer.jsp" %>