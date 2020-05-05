<template>
    <div>
        <!-- Modal -->
        <div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="saveModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="saveModalLabel">编辑课程</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                                        <div class="form-group">
                                            <label for="name">名称</label>
                                            <input type="text" v-model="courseToEdit.name" class="form-control" id="name"
                                                   placeholder="名称">
                                        </div>
                                        <div class="form-group">
                                            <label for="summary">概述</label>
                                            <input type="text" v-model="courseToEdit.summary" class="form-control" id="summary"
                                                   placeholder="概述">
                                        </div>
                                        <div class="form-group">
                                            <label for="time">时长</label>
                                            <input type="text" v-model="courseToEdit.time" class="form-control" id="time"
                                                   placeholder="时长">
                                        </div>
                                        <div class="form-group">
                                            <label for="price">价格（元）</label>
                                            <input type="text" v-model="courseToEdit.price" class="form-control" id="price"
                                                   placeholder="价格（元）">
                                        </div>
                                        <div class="form-group">
                                            <label for="image">封面</label>
                                            <input type="text" v-model="courseToEdit.image" class="form-control" id="image"
                                                   placeholder="封面">
                                        </div>

                                        <div class="form-group">
                                            <label for="level">级别</label>
                                            <select v-model="courseToEdit.level" class="form-control" id="level">
                                                <option v-for="o in COURSE_LEVEL" :value="o.key" :key="o.value">{{o.value}}</option>
                                            </select>
                                        </div>


                                        <div class="form-group">
                                            <label for="charge">收费</label>
                                            <select v-model="courseToEdit.charge" class="form-control" id="charge">
                                                <option v-for="o in COURSE_CHARGE" :value="o.key" :key="o.value">{{o.value}}</option>
                                            </select>
                                        </div>


                                        <div class="form-group">
                                            <label for="status">状态</label>
                                            <select v-model="courseToEdit.status" class="form-control" id="status">
                                                <option v-for="o in COURSE_STATUS" :value="o.key" :key="o.value">{{o.value}}</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="enroll">报名数</label>
                                            <input type="text" v-model="courseToEdit.enroll" class="form-control" id="enroll"
                                                   placeholder="报名数">
                                        </div>
                                        <div class="form-group">
                                            <label for="sort">顺序</label>
                                            <input type="text" v-model="courseToEdit.sort" class="form-control" id="sort"
                                                   placeholder="顺序">
                                        </div>
                                        <div class="form-group">
                                            <label for="teacherId">讲师</label>
                                            <input type="text" v-model="courseToEdit.teacherId" class="form-control" id="teacherId"
                                                   placeholder="讲师">
                                        </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="saveOneCourse">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <P class="row">
                    <button class="btn btn-white btn-default btn-round" @click="showEditCourseDialog(null)">
                        <i class="ace-icon fa fa-save red2"></i>
                        新增
                    </button>
                    &nbsp;
                    <button class="btn btn-white btn-default btn-round"
                            @click="getCourseList(1,$refs.pagination.size)">
                        <i class="ace-icon fa fa-refresh red2"></i>
                        刷新
                    </button>
                </P>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-6 col-sm-4 col-md-3 one-course" v-for="course in tableData.rows" :key="course.id">
                            <div class="thumbnail search-thumbnail one-course-inner">
                                <span class="search-promotion label label-success arrowed-in arrowed-in-right" v-if="course.time">{{course.time | formatSecond}}</span>

                                <img v-if="!course.image" class="media-object" data-src="holder.js/100px200?theme=gray" alt="100%x200" style="height: 200px; width: 100%; display: block;" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22284%22%20height%3D%22200%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20284%20200%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_171e2a0c082%20text%20%7B%20fill%3A%23AAAAAA%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A14pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_171e2a0c082%22%3E%3Crect%20width%3D%22284%22%20height%3D%22200%22%20fill%3D%22%23EEEEEE%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22105.671875%22%20y%3D%22106.3%22%3E284x200%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-holder-rendered="true">
                                <img v-else :src="course.image" :alt="course.name">
                                <div class="caption">
                                    <div class="clearfix">
                                        <span class="pull-right label label-grey info-label">{{course.price}}元</span>

                                        <div class="pull-left bigger-110">
                                            <i class="ace-icon fa fa-star orange2"></i>

                                            <i class="ace-icon fa fa-star orange2"></i>

                                            <i class="ace-icon fa fa-star orange2"></i>

                                            <i class="ace-icon fa fa-star-half-o orange2"></i>

                                            <i class="ace-icon fa fa-star light-grey"></i>
                                        </div>
                                    </div>

                                    <h3 class="search-title">
                                        <a href="#" class="blue" :title="course.name">{{course.name}}</a>
                                    </h3>
                                    <p class="summary" :title="course.summary">{{course.summary}}</p>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-info" @click="showEditCourseDialog(course)">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </button>

                                            <button class="btn btn-xs btn-danger" @click="delOneCourse(course.id)">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
                                                           title="Edit" @click="showEditCourseDialog(course)">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                                        </a>
                                                    </li>

                                                    <li>
                                                        <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete"
                                                           @click="delOneCourse(course.id)">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <pagination :item-count="5" :total="tableData.total" :size="currentPageConfig.tableSize"
                                    :items="[1,4,12,32,48,100]"
                                    @list="getCourseList" @selectChanged="paginationSelectChanged" ref="pagination"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {getCourseList, addOneCourse, editOneCourse, delOneCourse} from "api/admin/course";
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
                courseToEdit: {
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
                    tableSize: 4,
                    showLoading: false
                },
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_CHARGE: COURSE_CHARGE,
                COURSE_STATUS: COURSE_STATUS,
            }
        },
        methods: {
            getCourseList(page, size) {
                let _this = this;
                getCourseList(page, size).then(data => {
                    _this.tableData = data.data
                    //由于数据的更新，等dom更新以后，$nextTick里的操作会被执行
                    _this.$nextTick(() => _this.$refs.pagination.render(page))
                }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            paginationSelectChanged(currentPageSize) {
                let _this = this;
                _this.currentPageConfig.tableSize = currentPageSize;
                _this.getCourseList(1, this.currentPageConfig.tableSize)
            },
            //如果传入了course，说明是编辑，否则是新增
            showEditCourseDialog(course) {
                let _this = this
                if (course) {
                    //因为直接用course会触发界面的修改，所以复制一份对象，用复制的对象去修改
                    _this.courseToEdit = Object.assign({}, course)
                } else {

                    _this.courseToEdit = {
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
                    }
                }
                $('#saveModal').modal('show')
            },
            saveOneCourse() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.courseToEdit.name, "名称")
                    || !Validator.length(_this.courseToEdit.name, "名称", 1, 50)
                    || !Validator.length(_this.courseToEdit.summary, "概述", 1, 2000)
                    || !Validator.length(_this.courseToEdit.image, "封面", 1, 100)
                ) {
                    return;
                }

                addOneCourse(_this.courseToEdit)
                    .then(success => {
                        if (success) {
                            _this.getCourseList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            editOneCourse() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.courseToEdit.name, "名称")
                    || !Validator.length(_this.courseToEdit.name, "名称", 1, 50)
                    || !Validator.length(_this.courseToEdit.summary, "概述", 1, 2000)
                    || !Validator.length(_this.courseToEdit.image, "封面", 1, 100)
                ) {
                    return;
                }

                editOneCourse(_this.courseToEdit)
                    .then(success => {
                        if (success) {
                            _this.getCourseList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            delOneCourse(id) {
                let _this = this;
                new MessageBox({
                    message: '您确认要删除课程吗？',
                    callback: function (result) {
                        MaskLoading.start()
                        if (result.value) {
                            delOneCourse(id).then(success => {
                                _this.getCourseList(1, _this.currentPageConfig.tableSize)

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
            }
        },
        created() {
            this.getCourseList(1, this.currentPageConfig.tableSize)
        },
        mounted() {
        },
    }
</script>

<style lang="stylus" scoped>
    .one-course
        .one-course-inner
            height 350px
    .summary, .search-title
        white-space nowrap
        text-overflow ellipsis
        overflow hidden
</style>

