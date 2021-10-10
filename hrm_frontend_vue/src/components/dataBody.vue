<template>
    <tbody>
        <tr v-for="(object,index) in all" :key="'object'+index" v-show="searchResult(object)">
            <td class="column_id width_10">
                <span>{{object.id}}</span>
            </td>
            <td class="column_name width_10">
                <span v-show="object.id!==updateData.id">{{object.name}}</span>
                <input v-show="object.id===updateData.id" type="text" v-model="updateData.name">
            </td>
            <td class="column_gender width_6">
                <span v-show="object.id!==updateData.id">{{object.gender}}</span>
                <select  v-show="object.id===updateData.id" 
                         name="updategenders" id="updategenders" v-model="updateData.gender">
                    <option v-for="(gender,index) in genders" :key="'gender'+index" :value="gender">
                        {{gender}}
                    </option>
                </select>
            </td>
            <td class="width_15">
                <span  v-show="object.id!==updateData.id">{{birthday(object.birthday)}}</span>
                <input v-show="object.id===updateData.id" type="date" v-model="updateData.birthday">
            </td>
            <td >
                <span v-show="object.id!==updateData.id">{{object.other}}</span>
                <input v-show="object.id===updateData.id" type="text" v-model="updateData.other">
            </td>
            <td class="width_10">
                <span v-show="object.id!==updateData.id">{{section[object.section_id-1].name}}</span>
                <select v-show="object.id===updateData.id" name="updateSection" id="updateSection" v-model="updateData.section_id">
                    <option v-for="(sectionName,index) in section" :key="'sectionName'+index" :value="sectionName.id">
                        {{sectionName.name}}
                    </option>
                </select>
            </td>
            <td class="width_10">
                <span v-show="object.id!==updateData.id">{{post[object.post_id-1].name}}</span>
                <select v-show="object.id===updateData.id" name="updatePost" id="updatePost" v-model="updateData.post_id">
                    <option v-for="(postName,index) in post" :key="'sectionName'+index" :value="postName.id">
                        {{postName.name}}
                    </option>
                </select>
            </td>
            <td id="edit_td">
                <button @click="updateMethed(object)">
                    <i :class="'fa '+(object.id===updateData.id?'fa-check':'fa-pencil')" aria-hidden="true"></i>
                </button>
                <button v-show="object.id===updateData.id" @click="updateDataCancel">
                        <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <button v-show="object.id!==updateData.id" @click="deleteData(object.id)">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </button>
            </td>
        </tr>
    </tbody>
</template>

<script>
import axios from 'axios';

export default {
    name: 'databody',
    data(){
        return {
            genders:['男','女'],
            updateData:{
                id: "",
                name:"",
                gender:"",
                birthday:"",
                other:"",
                section_id:"",
                post_id:"",
            }
        }
    },
    props:{
        all: {
            type: Array
        },
        post: {
            type: Array
        },
        section: {
            type: Array
        },
        search_prop:{
            type:Object
        }
    },
    computed: {

    },
    methods:{
        //検索
        searchResult(object){
            if(!this.search_prop.isSearch){
                return true
            }else{
                //部分一致
                const search_regExp = new RegExp(`^(?=.*${this.search_prop.search_text}).*$`)
                return search_regExp.test(object[this.search_prop.search_field])
            }
        },
        birthday(unix){
            const unix_time = unix * 1000,
                    date = new Date(unix_time),
                    year = date.getFullYear(),
                    month = date.getMonth()+1,
                    day = date.getDate(),
                    birthday = `${year}/${month}/${day}`;
            return birthday
        },
        //データ編集条件判断
        updateMethed(object){
            if(this.updateData.id){
                //誕生日を変更するとbirthdayがStingになります
                if(typeof (this.updateData.birthday) === "string"){
                    //誕生日をUNIX TIMEに変換します
                    const toUnix = new Date(this.updateData.birthday).getTime()/1000
                    this.updateData.birthday = toUnix
                }
                this.updateDataAxios(this.updateData)
            }else{
                this.updateData = JSON.parse(JSON.stringify(object))
            }
        },
        //編集データ初期化
        updateDataCancel(){
            this.updateData.id = "";
            this.updateData.name = "";
            this.updateData.gender = "";
            this.updateData.birthday = "";
            this.updateData.other = "";
            this.updateData.sectionId = "";
            this.updateData.post_id = ""
        },
        //データ編集
        async updateDataAxios(params){
            const url = "/api/hrData/update";
            await axios.put(url,params)
            .then(()=>{
                 alert("変更成功")
                 this.updateDataCancel()
                 })
            .catch((err)=>{
                 alert("変更失敗");
                 console.log('Error! Counld not reach the API(updateData):'+err)
            })
        },
        //データ削除
        async deleteData(id){
            const deletConfirm = window.confirm("削除しますか")
            if(deletConfirm){
                const url = `/api/hrData/delete/${id}`
                await axios.delete(url)
                .then(()=>{
                    alert("削除成功")
                })
                .catch((err)=>{
                    alert("削除失敗")
                    console.log('Error! Counld not reach the API(deleteData):'+err)
                })
            }
        },
    }
}
</script>

<style lang="scss" scoped>
td{
    border-width:0.5px;
    border-color:black;
    border-style:solid;
    height:40px;
    font-size:15px
}

#edit_td{
    width:60px;
}

button{
    background:transparent;
    border:none;
    &:hover{
        color:grey;
    }
}

tr:nth-child(even){
    background:rgb(240, 240, 240)
}

.width{
    &_6{
        width:6%;
    };
    &_10{
        width:10%;
    };
    &_15{
        width:15%
    }
}

.column_name input{
    width:100%;
    text-align:center
}

</style>