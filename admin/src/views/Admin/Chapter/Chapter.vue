<template>
    <div>
        <!-- Modal -->
        <div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="saveModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="saveModalLabel">编辑大章</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="name">大章名称</label>
                                <input type="text" v-model="chapterToEdit.name" class="form-control" id="name"
                                       placeholder="大章名称">
                            </div>
                            <div class="form-group" v-if="!editingCourse.id">
                                <label for="courseId">课程ID</label>
                                <input type="text" v-model="chapterToEdit.courseId" class="form-control" id="courseId"
                                       placeholder="课程ID">
                            </div>
                            <div class="form-group" v-else>
                                <label for="courseId">课程名称</label>
                                <input type="text" v-model="editingCourse.name" class="form-control" id="courseName"
                                       placeholder="课程名称" readonly>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="saveOneChapter">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <h1 class="lighter" v-if="(!!$route.params.courseId && $route.params.courseId!=='null')">
                    <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
                    <router-link to="/business/course" class="pink">
                        {{editingCourse.name}}
                    </router-link>
                </h1>
                <P>
                    <button class="btn btn-white btn-default btn-round"
                            @click="$router.push({path:'/business/course'})" v-if="$route.params.courseId">
                        <i class="ace-icon fa fa-reply red2"></i>
                        返回课程
                    </button>
                    &nbsp;
                    <button class="btn btn-white btn-default btn-round" @click="showEditChapterDialog(null)">
                        <i class="ace-icon fa fa-save red2"></i>
                        新增
                    </button>
                    &nbsp;
                    <button class="btn btn-white btn-default btn-round"
                            @click="getChapterList(1,$refs.pagination.size)">
                        <i class="ace-icon fa fa-refresh red2"></i>
                        刷新
                    </button>
                </P>
                <table id="chapterTable" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>大章ID</th>
                        <th>大章名称</th>
                        <th>课程ID</th>

                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="chapter in tableData.rows" :key="chapter.id">
                        <td>{{chapter.id}}</td>
                        <td>{{chapter.name}}</td>
                        <td>{{chapter.courseId}}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-success"
                                        @click="jumpToSectionOfThisChapter(chapter.courseId,chapter.id)">
                                    <i class="ace-icon fa fa-desktop bigger-120"></i>
                                    小节
                                </button>

                                <button class="btn btn-xs btn-info" @click="showEditChapterDialog(chapter)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    编辑
                                </button>

                                <button class="btn btn-xs btn-danger" @click="delOneChapter(chapter.id)">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    删除
                                </button>
                            </div>

                            <div class="hidden-md hidden-lg">
                                <div class="inline pos-rel">
                                    <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                            data-position="auto">
                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                    </button>

                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                        <li>
                                            <a href="javascript:void(0);" class="tooltip-success" data-rel="tooltip"
                                               title="跳转至小节" @click="jumpToSectionOfThisChapter(chapter.courseId,chapter.id)">
																			<span class="green">
																				<i class="ace-icon fa fa-desktop bigger-120"></i>
																			</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="javascript:void(0);" class="tooltip-success" data-rel="tooltip"
                                               title="编辑" @click="showEditChapterDialog(chapter)">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="javascript:void(0);" class="tooltip-error" data-rel="tooltip" title="删除"
                                               @click="delOneChapter(chapter.id)">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <pagination :item-count="5" :total="tableData.total" :size="currentPageConfig.tableSize"
                            :items="[1,5,10,30,50,100]"
                            @list="getChapterList" @selectChanged="paginationSelectChanged" ref="pagination"/>
            </div><!-- /.span -->
        </div><!-- /.row -->
    </div>
</template>

<script>
    import {
        getChapterList,
        getChapterListByCourse,
        addOneChapter,
        editOneChapter,
        delOneChapter
    } from "api/admin/chapter";
    import {getCourseById} from "api/admin/course";
    import Pagination from "components/Pagination/Pagination";
    import {MessageBox, Swal} from "common/utils/SweetAlert2";
    import MaskLoading from "common/utils/LoadingMask";
    import Validator from "common/utils/validator";

    export default {
        name: "Index",
        components: {Pagination},
        data() {
            return {
                tableData: [],
                chapterToEdit: {
                    id: null,
                    name: null,
                    courseId: null
                },
                editingCourse: {
                    id: null,
                    name: null,
                    summary: null,
                    time: null,
                    price: null,
                    image: null,
                    level: null,
                    charge: null,
                    status: null,
                    enroll: null,
                    sort: null,
                    teacherId: null,
                },
                currentPageConfig: {
                    tableSize: 5,
                    showLoading: false
                }
            }
        },
        methods: {
            getChapterList(page, size) {
                let _this = this;
                const courseId = _this.$route.params.courseId
                _this.chapterToEdit.courseId = courseId
                if (!courseId) {
                    getChapterList(page, size).then(data => {
                        _this.tableData = data.data
                        //由于数据的更新，等dom更新以后，$nextTick里的操作会被执行
                        _this.$nextTick(() => _this.$refs.pagination.render(page))
                    }).catch(reason => {
                        new MessageBox({
                            message: reason.msg,
                            icon: 'error'
                        }).toast()
                    })
                } else {
                    getCourseById(courseId).then(data => {
                        _this.editingCourse = data.data
                    }).catch(reason => {
                        new MessageBox({
                            message: reason.msg,
                            icon: 'error'
                        }).toast()
                    })
                    getChapterListByCourse(page, size, courseId).then(data => {
                        _this.tableData = data.data

                        //由于数据的更新，等dom更新以后，$nextTick里的操作会被执行
                        _this.$nextTick(() => _this.$refs.pagination.render(page))
                    }).catch(reason => {
                        new MessageBox({
                            message: reason.msg,
                            icon: 'error'
                        }).toast()
                    })
                }
            },
            paginationSelectChanged(currentPageSize) {
                let _this = this;
                _this.currentPageConfig.tableSize = currentPageSize;
                _this.getChapterList(1, this.currentPageConfig.tableSize)
            },
            //如果传入了chapter，说明是编辑，否则是新增
            showEditChapterDialog(chapter) {
                let _this = this
                if (chapter) {
                    //因为直接用chapter会触发界面的修改，所以复制一份对象，用复制的对象去修改
                    _this.chapterToEdit = Object.assign({}, chapter)
                } else {

                    const courseId = _this.$route.params.courseId
                    _this.chapterToEdit = {
                        id: null,
                        name: null,
                        courseId: courseId
                    }
                }
                $('#saveModal').modal('show')
            },
            saveOneChapter() {
                let _this = this;

                // 保存校验
                if (!Validator.require(_this.chapterToEdit.name, "名称")
                    || !Validator.require(_this.chapterToEdit.courseId, "课程ID")
                    || !Validator.length(_this.chapterToEdit.courseId, "课程ID", 1, 8)) {
                    return;
                }

                addOneChapter(_this.chapterToEdit)
                    .then(success => {
                        if (success) {
                            _this.getChapterList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            editOneChapter() {
                let _this = this;

                // 保存校验
                if (!Validator.require(_this.chapterToEdit.name, "名称")
                    || !Validator.require(_this.chapterToEdit.courseId, "课程ID")
                    || !Validator.length(_this.chapterToEdit.courseId, "课程ID", 1, 8)) {
                    return;
                }

                editOneChapter(_this.chapterToEdit)
                    .then(success => {
                        if (success) {
                            _this.getChapterList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            delOneChapter(id) {
                let _this = this;
                new MessageBox({
                    message: '您确认要删除大章吗？',
                    callback: function (result) {
                        MaskLoading.start()
                        if (result.value) {
                            delOneChapter(id).then(success => {
                                _this.getChapterList(1, _this.currentPageConfig.tableSize)

                                MaskLoading.complete()
                                new MessageBox({
                                    message: '删除成功！',
                                    icon: 'success'
                                }).toast()
                            })
                            // For more information about handling dismissals please visit
                            // https://sweetalert2.github.io/#handling-dismissals
                        } else if (result.dismiss === Swal.DismissReason.cancel) {
                            MaskLoading.complete()
                            new MessageBox({
                                message: '删除已取消！您的数据很安全。',
                                icon: 'info'
                            }).alert()
                        }
                    }
                }).comfirm()
            },
            jumpToSectionOfThisChapter(courseId, chapterId) {
                this.$router.push({path: '/business/section?courseId=' + courseId + '&chapterId=' + chapterId})
            }
        },
        created() {
            this.getChapterList(1, this.currentPageConfig.tableSize)
        },
        mounted() {
        },
    }
</script>

<style lang="stylus" scoped>
</style>