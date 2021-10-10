<template>
    <div class="loginContent">
        <form @submit.prevent="login">
            <div class="title">DEMO人事管理システム</div>
            <div class="inputFormat">
                <label for="user_name">名前を入力してください</label>
                <input type="text" id ="user_name" name="user_name" v-model="user_name" required>
            </div>
            <button type="submit"> ログイン </button>
        </form>
    </div>
</template>

<script>
export default {
    name: 'Login',
    data() {
        return{
            user_name:"",
        }
    },
    methods: {
        login(){
            this.$store.dispatch("login", this.user_name.trim());
        },
    }
}
</script>

<style lang="scss" scoped>
.loginContent{
    border: 1px solid black;
    position: absolute;
    left:50%;
    top:30%;
    transform:translate(-50%,-50%);
    width: 400px;
    height: 300px;

    & form{
        @extend %display-flex;
        @extend %flex-direction-column;
        @extend %align-items-center;
        
        & .title{
            @extend %display-flex;
            @extend %justify-content-center;
            @extend %align-items-center;
            height: 150px;
            font-weight: bolde;
            font-size: 30px;
        }

        & .inputFormat{
                @extend %display-flex;
                @extend %flex-direction-column;
                @extend %align-items-center;
                width: 200px;
        }

        & button{
            border-style:solid;
            border-width:1px;
            border-color:black;
            background:white;
            @include button-hover(black, white, grey);
            width:100px;
            margin-top:40px;
        }
    }

}
</style>
