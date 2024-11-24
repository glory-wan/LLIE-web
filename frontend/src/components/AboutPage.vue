<template>
    <div class="about-top">
        <h1 class="about-top-one">实验室介绍</h1>
    </div>
    <div class="introduce">
        <div class="introduce-list">
            <a href="#">
                <img src="../../images/lls.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>指导老师：刘俊</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://shazhou01.github.io/">
                <img src="../../images/djz.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>杜锦洲</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="#">
                <img src="../../images/hjz.webp" alt="">
            </a>
            <div class="introduce-list-two">
                <p>洪佳智</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://humbleb11.github.io/">
                <img src="../../images/hlf.webp" alt="">
            </a>
            <div class="introduce-list-two">
                <p>黄林峰</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://mystic2004.github.io/">
                <img src="../../images/lhh.webp" alt="">
            </a>
            <div class="introduce-list-two">
                <p>刘桦桦</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://ln3214.github.io/">
                <img src="../../images/lwj.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>罗文俊</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://7dayu6.github.io/">
                <img src="../../images/lxq.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>刘小琪</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="#">
                <img src="../../images/slh.webp" alt="">
            </a>
            <div class="introduce-list-two">
                <p>石良昊</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="http://www.sunzimu.xyz/">
                <img src="../../images/szm.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>孙子木</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://kyletang-0711.github.io/">
                <img src="../../images/tt.webp" alt="">
            </a>
            <div class="introduce-list-two">
                <p>唐堂</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://glory-wan.cn/">
                <img src="../../images/wgr.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>万光荣</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="#">
                <img src="../../images/zhl.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>朱昊良</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="https://bainianzzz.github.io/bainianzzz-blog/">
                <img src="../../images/zqy.webp" alt="">
            </a>
            <div class="introduce-list-two">
                <p>周启飏</p>
            </div>
        </div>
        <div class="introduce-list">
            <a href="http://www.bz2116.cn/">
                <img src="../../images/zy.png" alt="">
            </a>
            <div class="introduce-list-two">
                <p>赵耀</p>
            </div>
        </div>

    </div>
</template>


<style>
.introduce-list-two {
    width: 250px;
    height: 200px;
}

.introduce-list-two p {
    margin-left: 20px;
}

.about-top {
    display: flex;
    white-space: nowrap;
    justify-content: center;
}

.about-top-one {
    width: 200px;
    height: 50px;
}

.about-top-two {
    width: 700px;
    line-height: 50px;
    height: 50px;
}

.introduce {
    display: flex;
    margin-top: 20px;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
}

.introduces {
    display: flex;
    flex-direction: row;
}

.introduce-list {
    display: flex;
    flex-direction: row;
    margin: 30px;
}

.introduce a {
    display: flex;
    flex-direction: column;
    text-align: center;
}

img {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    margin-bottom: 15px;
}

/* 手机端适配 */

@media screen and (max-width: 768px) {
    img {
        width: 100px;
        height: 100px;
    }

    .introduce-list {
        margin-right: 50px;
    }

    .introduce {
        justify-content: space-around;
    }

    .about-top-two {
        display: block;
        margin-top: 60px;
        margin-left: -160px;
    }
}

@media screen and (max-width: 480px) {
    .introduce {
        flex-direction: column;
        align-items: center;
    }

    img {
        width: 150px;
        height: 150px;
    }

    .introduce-list {
        margin: 10px 0;
    }
}
</style>