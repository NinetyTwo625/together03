<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@ include file="../layout/header.jsp" %>

    <!--사진 업로드페이지 중앙배치-->
        <main class="uploadContainer">
           <!--사진업로드 박스-->
            <section class="upload">

               <!--사진업로드 로고-->
                <div class="upload-top">
                    <a href="home.html" class="">
                        <img src="/images/logo.jpg" alt="">
                    </a>
                    <p>사진 업로드</p>
                </div>
                <!--사진업로드 로고 end-->

                <!--사진업로드 Form-->
                <form id="uploadForm" class="upload-form">
                    <input  type="file" name="file" onchange="imageChoose(this)"/>
                    <div class="upload-img">
                        <img src="/images/person.jpeg" alt="" id="imageUploadPreview" />
                    </div>

                    <!--사진설명 + 업로드버튼-->
                    <div class="upload-form-detail">
                   		<input type="text" placeholder="사진을 설명해보세요!" name="caption">

                   		<!-- 해시 태그 -->
                   		<div class="upload-form-hashtag" align="center">
                   			<input type="text" placeholder="해시태그를 추가해보세요!" id="hashtag" name="hashtag">
                   			<button type="button" class="cta blue" onclick="addHashtag()">추가</button>
                   		</div>

                   		<!-- 추가한 해시 태그 리스트 -->
                   		<div class="hashtagList" id="hashtagList">
                   		</div>
                   		<!-- 해시 태그 end -->

                        <button type="button" id="uploadBtn" class="cta blue">업로드</button>
                    </div>
                    <!--사진설명end-->

                </form>
                <!--사진업로드 Form-->
            </section>
            <!--사진업로드 박스 end-->
        </main>
        <br/><br/>

	<script src="/js/upload.js" ></script>
    <%@ include file="../layout/footer.jsp" %>