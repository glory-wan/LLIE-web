<template>
    <h1>实验室介绍</h1>
    <p>项目地址：<a href="https://github.com/glory-wan/LLIE-Lib">LLIE-Lib</a></p>
    <div class="introduce">
        <div class="introduce-list">
            <a href="#"><img
                    src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">1111</a>
        </div>
        <div class="introduce-list">
            <a href="#"><img
                    src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">1111</a>
        </div>
        <div class="introduce-list">
            <a href="#"><img
                    src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">1111</a>
        </div>
        <div class="introduce-list">
            <a href="#"><img
                    src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">1111</a>
        </div>
        <div class="introduce-list">
            <a href="#"><img
                    src="https://ts1.cn.mm.bing.net/th/id/R-C.9c4beeff5af1abc0ef6c7ba3d673bfe9?rik=4wuGUMDXxudlgA&riu=http%3a%2f%2fimg.netbian.com%2ffile%2f2023%2f0329%2f2257209tzEy.jpg&ehk=i%2brXr7T%2bso1jlmUXGw0QSlpyI7Q11MNl2d9EkRdff6g%3d&risl=&pid=ImgRaw&r=0"
                    alt="">1111</a>
        </div>
    </div>
</template>


<style>
.introduce {
    display: flex;
    margin-top: 20px;
    flex-direction: row;
    flex-wrap: wrap; 
    justify-content: center; 
}

.introduce-list {
    display: flex;
    flex-direction: column;
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