<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<style>
/* 기본 */
@import url("https://fonts.googleapis.com/icon?family=Material+Icons");

/* Outlined */
@import url("https://fonts.googleapis.com/icon?family=Material+Icons+Outlined");

/* Rounded */
@import url("https://fonts.googleapis.com/icon?family=Material+Icons+Round");

/* Sharp */
@import url("https://fonts.googleapis.com/icon?family=Material+Icons+Sharp");




.display-flex-center {
    display: flex;
    justify-content: center;
    align-items: center;
}

.position-relative {
    position: relative;
}

.find-search-bar-form {
    padding: 0 25px;
}

.find-search-bar-form div {
    width: 100%;
}

.find-search-bar {
    margin-top: 20px;
    margin-bottom: 20px;
    background-color: var(--white);
    border-radius: 25px;
    width: 700px;
    padding: 10px;
    color: white;
    transition: background-color 0.3s ease-in-out;
}

.find-search-bar:focus {
    background-color: #FFBFBF;
    color : black;
}

 input[type=submit], input[type=reset] {
        padding: 10px 20px;
        border: none;
        background-color: #FFBFBF; /* 버튼 배경색 */
        color: #fff; /* 버튼 글자색 */
        cursor: pointer;
        margin: 10px 20px;
        transition: background-color 0.3s ease-in-out;
        border-radius: 4px;
        
}
    


</style>  

  <!-- 검색바 시작 -->  
<form action="SearchRes.re" method="get" class="find-serarch-bar-form display-flex-center position-relative">
    <div class="display-flex-center position-relative">
				<input type="search" class="find-search-bar" name="serch">
				<input type="submit" value="검색">
    </div>
</form>
<!-- 검색바 끝 -->
