<template>
    <div>
        <!-- Modal -->
        <div class="modal fade" id="saveModal" tabindex="-1" role="dialog" aria-labelledby="saveModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="saveModalLabel">编辑小节</h4>
                    </div>
                    <div class="modal-body">
                        <form>
                                        <div class="form-group">
                                            <label for="title">标题</label>
                                            <input type="text" v-model="sectionToEdit.title" class="form-control" id="title"
                                                   placeholder="标题">
                                        </div>
                                        <div class="form-group">
                                            <label for="courseId">课程</label>
                                            <input type="text" v-model="sectionToEdit.courseId" class="form-control" id="courseId"
                                                   placeholder="课程">
                                        </div>
                                        <div class="form-group">
                                            <label for="chapterId">大章</label>
                                            <input type="text" v-model="sectionToEdit.chapterId" class="form-control" id="chapterId"
                                                   placeholder="大章">
                                        </div>
                                        <div class="form-group">
                                            <label for="video">视频</label>
                                            <input type="text" v-model="sectionToEdit.video" class="form-control" id="video"
                                                   placeholder="视频">
                                        </div>
                                        <div class="form-group">
                                            <label for="time">时长</label>
                                            <input type="text" v-model="sectionToEdit.time" class="form-control" id="time"
                                                   placeholder="时长">
                                        </div>

                                        <div class="form-group">
                                            <label for="charge">收费</label>
                                            <select v-model="sectionToEdit.charge" class="form-control" id="charge">
                                                <option v-for="o in COURSE_CHARGE" :value="o.key" :key="o.value">{{o.value}}</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="sort">顺序</label>
                                            <input type="text" v-model="sectionToEdit.sort" class="form-control" id="sort"
                                                   placeholder="顺序">
                                        </div>
                                        <div class="form-group">
                                            <label for="vod">vod</label>
                                            <input type="text" v-model="sectionToEdit.vod" class="form-control" id="vod"
                                                   placeholder="vod">
                                        </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" @click="saveOneSection">保存</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <P>
                    <button class="btn btn-white btn-default btn-round" @click="showEditSectionDialog(null)">
                        <i class="ace-icon fa fa-save red2"></i>
                        新增
                    </button>
                    &nbsp;
                    <button class="btn btn-white btn-default btn-round"
                            @click="getSectionList(1,$refs.pagination.size)">
                        <i class="ace-icon fa fa-refresh red2"></i>
                        刷新
                    </button>
                </P>
                <table id="sectionTable" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>标题</th>
                        <th>课程</th>
                        <th>大章</th>
                        <th>视频</th>
                        <th>时长</th>
                        <th>收费</th>
                        <th>顺序</th>
                        <th>vod</th>

                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="section in tableData.rows" :key="section.id">
                        <td>{{section.id}}</td>
                        <td>{{section.title}}</td>
                        <td>{{section.courseId}}</td>
                        <td>{{section.chapterId}}</td>
                        <td>{{section.video}}</td>
                        <td>{{section.time}}</td>
                        <td>{{COURSE_CHARGE | optionKV(section.charge)}}</td>
                        <td>{{section.sort}}</td>
                        <td>{{section.vod}}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" @click="showEditSectionDialog(section)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>

                                <button class="btn btn-xs btn-danger" @click="delOneSection(section.id)">
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
                                               title="Edit" @click="showEditSectionDialog(section)">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete"
                                               @click="delOneSection(section.id)">
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
                            @list="getSectionList" @selectChanged="paginationSelectChanged" ref="pagination"/>
            </div><!-- /.span -->
        </div><!-- /.row -->
    </div>
</template>

<script>
    import {getSectionList, addOneSection, editOneSection, delOneSection} from "api/admin/section";
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
                sectionToEdit: {
                    title: null,
                    courseId: null,
                    chapterId: null,
                    video: null,
                    time: null,
                    charge: null,
                    sort: null,
                    vod: null,
                },
                currentPageConfig: {
                    tableSize: 5,
                    showLoading: false
                },
                COURSE_CHARGE: COURSE_CHARGE,
            }
        },
        methods: {
            getSectionList(page, size) {
                let _this = this;
                getSectionList(page, size).then(data => {
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
                _this.getSectionList(1, this.currentPageConfig.tableSize)
            },
            //如果传入了section，说明是编辑，否则是新增
            showEditSectionDialog(section) {
                let _this = this
                if (section) {
                    //因为直接用section会触发界面的修改，所以复制一份对象，用复制的对象去修改
                    _this.sectionToEdit = Object.assign({}, section)
                } else {

                    _this.sectionToEdit = {
                    title: null,
                    courseId: null,
                    chapterId: null,
                    video: null,
                    time: null,
                    charge: null,
                    sort: null,
                    vod: null,
                    }
                }
                $('#saveModal').modal('show')
            },
            saveOneSection() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.sectionToEdit.title, "标题")
                    || !Validator.length(_this.sectionToEdit.title, "标题", 1, 50)
                    || !Validator.length(_this.sectionToEdit.video, "视频", 1, 200)
                ) {
                    return;
                }

                addOneSection(_this.sectionToEdit)
                    .then(success => {
                        if (success) {
                            _this.getSectionList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            editOneSection() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.sectionToEdit.title, "标题")
                    || !Validator.length(_this.sectionToEdit.title, "标题", 1, 50)
                    || !Validator.length(_this.sectionToEdit.video, "视频", 1, 200)
                ) {
                    return;
                }

                editOneSection(_this.sectionToEdit)
                    .then(success => {
                        if (success) {
                            _this.getSectionList(1, _this.currentPageConfig.tableSize)
                            $('#saveModal').modal('hide')
                        }
                    }).catch(reason => {
                    new MessageBox({
                        message: reason.msg,
                        icon: 'error'
                    }).toast()
                })
            },
            delOneSection(id) {
                let _this = this;
                new MessageBox({
                    message: '您确认要删除小节吗？',
                    callback: function (result) {
                        MaskLoading.start()
                        if (result.value) {
                            delOneSection(id).then(success => {
                                _this.getSectionList(1, _this.currentPageConfig.tableSize)

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
            this.getSectionList(1, this.currentPageConfig.tableSize)
        },
        mounted() {
        },
    }
</script>

<style lang="stylus" scoped>
</style>

