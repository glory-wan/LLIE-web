<template>
    <div class="about-top">
        <h1 class="about-top-one">实验室介绍</h1>
        <!-- <p style="margin-left: 100px;">项目地址：<a href="https://github.com/glory-wan/LLIE-Lib">LLIE-Lib</a></p> -->
        <p class="about-top-two">实验室简介</p>
    </div>
    <div class="introduce">
        <div class="introduce-list">
            <a href="#">
                <img src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">
            </a>
            <div class="introduce-list-two"><p>简介</p></div>
        </div>
        <div class="introduce-list">
            <a href="#">
                <img src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">
            </a>
            <div class="introduce-list-two"><p>简介</p></div>
        </div> 
        <div class="introduce-list">
            <a href="#">
                <img src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">
            </a>
            <div class="introduce-list-two"><p>简介</p></div>
        </div> 
        <div class="introduce-list">
            <a href="#">
                <img src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">
            </a>
            <div class="introduce-list-two"><p>简介</p></div>
        </div>

    </div>
</template>


<style>
.introduce-list-two{
    width: 250px;
    height: 200px;
    /* background: #8f7e7e; */
}
.introduce-list-two p{
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
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
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
    width: 200px;
    height: 200px;
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