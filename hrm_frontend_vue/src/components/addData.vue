<template>
    <div class="add_content">
        <div id="close_button">
           <i class="fa fa-times" aria-hidden="true" @click="close()" style="cursor:pointer"></i>
        </div>
        <form @submit.prevent="addData">
            <div>
                <label for="id">社員番号</label>
                <input type="number" v-model="add_profile.id" min="10000000" max="99999999" required>
            </div>
            <div>
                <label for="name">名前</label>
                <input type="text" v-model="add_profile.name" required>
            </div>
            <div>
                <label for="gender">性別</label>
                <select name="gender" id="gender" v-model="add_profile.gender">
                    <option v-for="(gender,index) in genders" :key="'addData_gender'+index" :value="gender">
                        {{gender}}
                    </option>
                </select>
            </div>
            <div>
                <label for="birthday">誕生日</label>
                <input type="date" v-model="add_profile.birthday" id="birthday" required>
            </div>
            <div>
                <label for="other">備考</label>
                <input type="text" v-model="add_profile.other" id="other">
            </div>
            <div>
                <label for="section">事業部</label>
                <select name="section" id="section" v-model="add_profile.section_id" required>
                    <option v-for="(_section,index) in section" :key="'addData_section'+index" :value="_section.id">
                        {{_section.name}}
                    </option>
                </select>
            </div>
            <div>
                <label for="post">職位</label>
                <select name="post" id="post" v-model="add_profile.post_id" required>
                    <option v-for="(_post,index) in post" :key="'addData_post'+index" :value="_post.id">
                        {{_post.name}}
                    </option>
                </select>
            </div>
            <button type="submit">追加</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name:"addData",
    data(){
        return{
            genders:["男","女"],
            add_profile:{
                id: "",
                name:"",
                gender:"",
                birthday:"",
                other:null,
                section_id:"",
                post_id:"",
            }
        }
    },
    props:{
        section:{
            style: Array
        },
        post:{
            style: Array
        }
    },
    methods:{
        close(){
            this.$emit('closeAdd',false)
        },
        //データ追加
        async addData(){
            console.log(this.add_profile)
            //誕生日をUNIX TIMEに変換します
            const toUnix = new Date(this.add_profile.birthday).getTime()/1000
            this.add_profile.birthday = toUnix

            const url= '/api/hrData/add';
            await axios.post(url,this.add_profile)
            .then(()=>{
                this.add_profile.id = "";
                this.add_profile.name = "";
                this.add_profile.gender = "";
                this.add_profile.birthday = "";
                this.add_profile.other = "";
                this.add_profile.section_id = "";
                this.add_profile.post_id = ""
                this.$emit('closeAdd',false)
            })
            .catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style lang="scss" scoped>

.add_content{
    background-color:white;
    width: 40%;
    position: absolute;
    top: 20%;
    left: 50%;
    transform: translate(-50%);


    & #close_button{
        border-style:solid;
        border-width:1px;
        border-color:transparent transparent black  transparent;
        @extend %display-flex;
        @extend %justify-content-flex-end;
        padding:5px;
        
    }

    & form{
        margin-top:20px;
        margin-bottom:20px;
        @extend %display-flex;
        @extend %flex-direction-column;
        @extend %align-items-center;
        gap:10px;

        & div{
            @extend %display-flex;
            @extend %justify-content-space-between;
            width: 60%;
        }

        & button{
            margin-top:10px;
            border-color:black;
            border-style: solid;
            border-width:1px;
            background:white;
            @include button-hover(black,white,grey)
        }
    }
}
</style>