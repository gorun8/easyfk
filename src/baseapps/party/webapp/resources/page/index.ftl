<#--
Project:Easy Web Framework

Description: This project is based on much more open source projects than ever before,
             and can be applied to mostly web development environment.
Author:hezhiping   Email:110476592@qq.com


===================================================================================================


-->
<@easyfkSetAppMenu menuName="EASYFK_MENU" focusItemIds="{['menuHomeId']}" location="component://party/webapp/resources/page/appmenus.ftl">
</@easyfkSetAppMenu>

<@easyfkSetNavBar subTitle="首面">
<#--
{NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
]}
-->
</@easyfkSetNavBar>

<@easyfkDecoratorScreen name="body" location="component://party/webapp/resources/page/appcommon.ftl">

    <div class="row-fluid">
        <div class="span12 center" style="text-align: center;">
            <ul class="stat-boxes">
                <li>
                    <div class="left peity_bar_good"><span>2,4,9,7,12,10,12</span>+20%</div>
                    <div class="right">
                        <strong>36094</strong>
                        Visits
                    </div>
                </li>
                <li>
                    <div class="left peity_bar_neutral"><span>20,15,18,14,10,9,9,9</span>0%</div>
                    <div class="right">
                        <strong>1433</strong>
                        Users
                    </div>
                </li>
                <li>
                    <div class="left peity_bar_bad"><span>3,5,9,7,12,20,10</span>-50%</div>
                    <div class="right">
                        <strong>8650</strong>
                        Orders
                    </div>
                </li>
                <li>
                    <div class="left peity_line_good"><span>12,6,9,23,14,10,17</span>+70%</div>
                    <div class="right">
                        <strong>8650</strong>
                        Orders
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12 center" style="text-align: center;">
            <ul class="quick-actions">
                <li>
                    <a href="#">
                        <i class="icon-calendar"></i>
                        Manage Events
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-shopping-bag"></i>
                        Manage Orders
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-database"></i>
                        Manage DB
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-people"></i>
                        Manage Users
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-lock"></i>
                        Security
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-piechart"></i>
                        Statistics
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12 center" style="text-align: center;">
            <ul class="quick-actions-horizontal">
                <li>
                    <a href="#">
                        <i class="icon-calendar"></i>
                        <span>Manage Events</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-shopping-bag"></i>
                        <span>Manage Orders</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-database"></i>
                        <span>Manage DB</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-people"></i>
                        <span>Manage Users</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-lock"></i>
                        <span>Security</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="icon-piechart"></i>
                        <span>Statistics</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="alert">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Warning!</strong> Your monthly traffic is reaching limit.
            </div>
            <div class="alert alert-success">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Success!</strong> The page has been added.
            </div>
            <div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> You have 198 unread messages.
            </div>
            <div class="alert alert-error">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Error!</strong> The daily cronjob has failed.
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title">
                    <h5>Modal dialogs</h5>
                </div>
                <div class="widget-content">
                    <a href="#myModal" data-toggle="modal" class="btn btn-primary">Modal dialog</a>
                    <a href="#myAlert" data-toggle="modal" class="btn btn-danger">Alert</a>

                    <div id="myModal" class="modal hide">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button">×</button>
                            <h3>Modal header</h3>
                        </div>
                        <div class="modal-body">
                            <p>One fine body…</p>
                        </div>
                    </div>
                    <div id="myAlert" class="modal hide">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button">×</button>
                            <h3>Alert modal</h3>
                        </div>
                        <div class="modal-body">
                            <p>Lorem ipsum dolor sit amet...</p>
                        </div>
                        <div class="modal-footer">
                            <a data-dismiss="modal" class="btn btn-primary" href="#">Confirm</a>
                            <a data-dismiss="modal" class="btn" href="#">Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="widget-box">
                <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                    <h5>Progress bars</h5>
                </div>
                <div class="widget-content">

                    <h3>Basic progress bars</h3>
                    <div class="progress">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-success">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-warning">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-danger">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <h3>Striped progress bars</h3>
                    <div class="alert">
                        Internet Explorer doesn't support striped progress bars!
                    </div>
                    <div class="progress progress-striped">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-striped progress-success">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-striped progress-warning">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-striped progress-danger">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <h3>Animated progress bars</h3>
                    <div class="alert">
                        Internet Explorer doesn't support animated progress bars!
                    </div>
                    <div class="progress progress-striped active">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-striped progress-success active">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-striped progress-warning active">
                        <div style="width: 60%;" class="bar"></div>
                    </div>
                    <div class="progress progress-striped progress-danger active">
                        <div style="width: 60%;" class="bar"></div>
                    </div>

                </div>
            </div>
            <div class="widget-box">
                <div class="widget-title">
									<span class="icon">
										<i class="icon-comment"></i>
									</span>
                    <h5>Tooltip directions</h5>
                </div>
                <div class="widget-content">
                    <p>Four directions of the tooltips, just add a class: 'tip-top', 'tip-bottom', 'tip-left' or 'tip-right' without quotes to the element you want to have tooltip.</p>
                    <p>
                        <button class="btn tip-top" data-original-title="Tooltip in top">Top</button>
                        <button class="btn tip-left" data-original-title="Tooltip in left">Left</button>
                        <button class="btn tip-right" data-original-title="Tooltip in right">Right</button>
                        <button class="btn tip-bottom" data-original-title="Tooltip in bottom">Bottom</button>
                    </p>
                </div>
            </div>
            <div class="widget-box">
                <div class="widget-title">
								<span class="icon">
									<i class="icon-exclamation-sign"></i>
								</span>
                    <h5>Pagination</h5>
                </div>
                <div class="widget-content">
                    <h3>Default pagination</h3>
                    <div class="pagination">
                        <ul>
                            <li><a href="#">Prev</a></li>
                            <li class="active">
                                <a href="#">1</a>
                            </li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">Next</a></li>
                        </ul>
                    </div>
                    <h3>Alternate pagination</h3>
                    <code>&lt;div class="pagination alternate"&gt;</code>
                    <div class="pagination alternate">
                        <ul>
                            <li class="disabled"><a href="#">Prev</a></li>
                            <li class="active">
                                <a href="#">1</a>
                            </li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="span6">
            <div class="widget-box">
                <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                    <h5>Notifications</h5>
                </div>
                <div class="widget-content">
                    <div class="alert alert-block">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <h4 class="alert-heading">Warning!</h4>
                        Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
                    </div>
                    <div class="alert alert-success alert-block">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <h4 class="alert-heading">Success!</h4>
                        Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
                    </div>
                    <div class="alert alert-info alert-block">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <h4 class="alert-heading">Info!</h4>
                        Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
                    </div>
                    <div class="alert alert-error alert-block">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <h4 class="alert-heading">Error!</h4>
                        Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.
                    </div>
                </div>
            </div>
            <div class="widget-box">
                <div class="widget-title">
                    <h5>Labels and badges</h5>
                </div>
                <div class="widget-content">
                    <h3>Available labels</h3>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Labels</th>
                            <th>Markup</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <span class="label">Default</span>
                            </td>
                            <td>
                                <code>&lt;span class="label"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="label label-success">Success</span>
                            </td>
                            <td>
                                <code>&lt;span class="label label-success"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="label label-warning">Warning</span>
                            </td>
                            <td>
                                <code>&lt;span class="label label-warning"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="label label-important">Important</span>
                            </td>
                            <td>
                                <code>&lt;span class="label label-important"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="label label-info">Info</span>
                            </td>
                            <td>
                                <code>&lt;span class="label label-info"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <span class="label label-inverse">Inverse</span>
                            </td>
                            <td>
                                <code>&lt;span class="label label-inverse"&gt;</code>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <h3>Available badges</h3>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Example</th>
                            <th>Markup</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                Default
                            </td>
                            <td>
                                <span class="badge">1</span>
                            </td>
                            <td>
                                <code>&lt;span class="badge"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Success
                            </td>
                            <td>
                                <span class="badge badge-success">2</span>
                            </td>
                            <td>
                                <code>&lt;span class="badge badge-success"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Warning
                            </td>
                            <td>
                                <span class="badge badge-warning">4</span>
                            </td>
                            <td>
                                <code>&lt;span class="badge badge-warning"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Important
                            </td>
                            <td>
                                <span class="badge badge-important">6</span>
                            </td>
                            <td>
                                <code>&lt;span class="badge badge-important"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Info
                            </td>
                            <td>
                                <span class="badge badge-info">8</span>
                            </td>
                            <td>
                                <code>&lt;span class="badge badge-info"&gt;</code>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Inverse
                            </td>
                            <td>
                                <span class="badge badge-inverse">10</span>
                            </td>
                            <td>
                                <code>&lt;span class="badge badge-inverse"&gt;</code>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="widget-box">
                <div class="widget-title">
									<span class="icon">
										<i class="icon-comment"></i>
									</span>
                    <h5>Growl-like notifications</h5>
                </div>
                <div class="widget-content notify-ui">
                    <ul id="gritter-notify">
                        <li class="normal">Standard notification</li>
                        <li class="sticky">Sticky notification</li>
                        <li class="image" data-image="img/demo/envelope.png">Notification with image</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>



</@easyfkDecoratorScreen>



