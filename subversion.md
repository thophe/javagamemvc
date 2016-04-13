# Introduction #

CVS很酷，但Subversion更酷。然而，如果你在使用Eclipse进行开发，那么你可能直到近来才能利用Subversion带来的优点
摘要 CVS很酷，但Subversion更酷。然而，如果你在使用Eclipse进行开发，那么你可能直到近来才能利用Subversion带来的优点。随着Subclipse的发行，Subversion可能会最终在你的Eclipse IDE环境充分发挥其威力而压倒CVS。


# Details #

一、SCM和Subversion简介

软件配置管理(SCM)是管理源码并保持其安全的良好艺术，它能实现源码与其他团队成员之间保持共享，并且能够对之加以保护。良好地利用SCM，你能够容易地跟踪软件的发行和新的开发分支；这样以来，可以更为容易地标识和修正发行产品中的错误。

其实，有大量的SCM工具可用，既有开源的和也有商业化的，例如StarTeam，Perforce，BitKeeper和 ClearCase。在开源世界里，事实上的SCM标准是并发版本管理系统（CVS），它被广泛应用于世界范围内的成百上千的开源和商业工程。然而，CVS也存在下列许多固有的缺陷，这使得它无法非常完美地适合于现代工程开发：

· 实质上针对文本文件的设计使得CVS处理二进制文件能力比较差。在每一次提交时，二进制文件被以整体形式传输和存储，这将带来带宽和磁盘空间的浪费。

· 在CVS中，你不能移动文件和目录。你唯一的选择基本上就是删除并且重新添加它们，从而失去了整个过程中的所有的文件历史信息。

· CVS中没有实现原子提交的概念。比方说，你要把10个文件提交到服务器，而该提交操作往往在整个过程的中途停了下来。(这很可能会发生，如果某人同时提交一个文件，或甚至如果你的网络失败或你的PC重新启动的话。)在这种情况下，服务器将仅记录下你的修正的一半信息，这可能会使代码基部分处于一种潜在地不稳定的状态。

Subversion是一种比较新的开源SCM工具，其设计目的是力图从根本上克服原CVS所具有的限制。它是一种良好设计的工具，具有适合于现代开发的许多新特征：

· 提交是原子化的。提交的文件都能够被正确加入到一个新的修订当中，否则仓库不会被更新；并且每一个新的修订仅由一次提交中的变化部分组成。

· Subversion对文本和二进制文件使用一种巧妙的二进制技术，这既优化了网络流量也优化了仓库磁盘空间。

· 在Subversion中，每一次修订都代表了一个特定时间内完整的目录树拷贝。文件和目录可以不加限制地进行移动。

· Subversion仅存储两个版本之间的修改内容，这不仅节约了磁盘空间，并且意味着标识一个新版本或创建一种新的子内容几乎可以立即实现。

· 你可以以多种途径来存取一个Subversion仓库，具体则依赖于你的需要：使用HTTP或HTTPS（与WebDAV一起使用），使用快速的专利性svn:协议，或直接经由本地文件，等等。

二、Subclipse插件与Eclipse的集成

一种良好的SCM应该与你的工作环境紧密地集成到一起。没有谁真正喜欢转到命令行以把文件添加到仓库。Eclipse很早就实现了CVS集成，但是直到最近Subversion用户仍没有被引起重视。现在，新的Subclipse插件提供了在Eclipse中的一种平滑的Subversion集成。

(一) 安装Subclipse插件

下面，你以通常的方法从更新站点下安装Subclipse：

1. 打开"Find and install"窗口（"Help>Software Updates>Find and Install"）。

2. 选择"Search for new features to install"选项并点击Next。

3. 点击"New Remote Site"并且创建一远程站点，使用名字Subclipse和URL http://subclipse.tigris.org/update_1.0.x(参考图1)。

4. 在结果安装窗口中，把"Subeclipse in the Features"选择到安装列表中，并且通过向导来开始安装插件。

5. 完成这些之后，重新启动Eclipse。现在，你可以继续往下进行！


图1.安装Subclipse插件

(二) 建立Repository定义

现在，既然你已经安装完插件；那么，接下来，你需要告诉它你的工程仓库位于何处。你是在SVN Repository视图中实现的。打开这个视图（"Windows>Show View>Other>SVN Repository"）并且在上下文菜单中选择"New>Repository Location"以显示一个如图2所示的对话框。输入适当的URL并且点击"Finish"。


图2.添加一个仓库定义


(三) 检出（Check Out）一个工程

一旦建立一个仓库，你就可以在SVN Repository视图中浏览所有的内容(见图3)。我们后面将会看到，这个视图是一种与Subversion进行交互的非常方便的方式。



图3.SVN Repository视图。


现在，让我们把一个工程检出到你的Eclipse工作区中。这只需选择你需要的Subversion仓库，打开上下文菜单，并且选择"Checkout"即可。这将打开一个具有两个选项的向导：

· Check out as a Project configured using the New Project Wizard-这个选项打开新工程向导，这可以让你使用内建的Eclipse工程类型配置工程。这个选项通常是最好用的，因为它让你使用相同的工程模板和配置屏幕，而当你创建一个常规工程时你经常使用它们。

· Check out as a Project in the Workspace-这个选项简单地在你的包含检出源码的工作区中创建一个Eclipse工程。

在以上两种情况下，你仍然需要更新工程的构建路径，因为在检出该工程源码之前，Eclipse不能确定这些Java源码所在的位置。

(四) 把一个新工程导入到仓库中

如果你只是启动了一个新的工程，那么你需要把它导入到Subversion仓库。Subclipse提供了一种方便的方式来直接从你的IDE内部实现这一点。为此，只需要从Package Explorer视图下选择你的工程，并且在上下文菜单中选择"Team>Share Project"。你可以使用现有仓库之一或创建一新的仓库定义。在你指定仓库和工程名之后，你能指定你想放到仓库中的文件和目录并且提供一个初始注释 (见图4)。这种方法特别有用，因为它让你有选择地导入仅由Subversion管理的文件，即使该工程还包含其它文件（例如生成的类，临时文件或其它不是必需的内容等）。



图4.把一个工程导入到一个Subversion仓库中

三、在Eclipse中使用Subversion

现在，既然你的支持Subversion的工程已经启动并且运行起来，那么大多数必要的Subversion命令就可经由"Team"上下文菜单存取(参考图5)。你可以在Package Explorer中看到你的本地文件的状态(参考图6)，其中，任何修改了的文件都被标记上一个星号。存储在仓库中的文件都显示一个小黄桶图标(代表了一个数据库)；还没有被添加到仓库中的文件以一个问号显示。


图5.大多数Subversion命令能被经由Team菜单存取

图6.你可以在Package Explorer中看到本地文件的状态

(一) 与Repository保持同步

从仓库中更新你的文件并且把你的变化提交到仓库是相当直接的过程，这可以使用"Team>Update and Team>Commit"菜单选项来实现。在提交你的变化之前，你可能想看一下自从你的上次更新以来是否服务器上有任何文件被修改。为此，你可以使用"Team >Synchronize with Repository"。这个命令让你看到有哪些内容已经被局部地修改，有哪些内容在服务器上修改，以及这两种修改之间的任何冲突（参考图7)。你还可以以可视化方式看到冲突的版本，并且在提交你的变化之前纠正任何比较突出的冲突。


图7.与仓库保持同步

(二) 使用属性

属性是Subversion具有创新性的特征之一。在Subversion中，你可以把元数据（"properties"）关联到任何文件或目录。你可以定义任何你喜欢的属性，但是Subversion也提供了一些有用的内置属性，例如下面图8中所提供的这些属性：

· svn:executable属性，允许你在支持这种能力的操作系统上设置一个文件的可执行标志。

· svn:need-lock属性，可以用来在文件（例如，对二进制文件非常有用）上强加排斥锁。一个定义了svn:need-lock属性的文件一次只能被一个人修改。当该文件被检出时，它是只读的。如果你想修改该文件，你需要首先使用"Team>Lock"菜单选项。之后，使用"Team>Unlock"释放该文件，或仅提交你的变化。这一行为将释放该锁并且让其它的用户也得到该文件上的一把锁。


图8.把一个Subversion属性添加到一个文件中

三) Tag和Branch

在Subversion中，很容易创建新的tag和branch。你可以使用 tag来标识一个特定的版本（使用一种可读的名字，例如"Release 1.0"）。；而一个branch用于新的开发工作而不影响主源码基(称作trunk)。在一个branch上的开发仍会继续进行，直到开发者已经为把变化集成回主trunk作好准备。

在Subversion中，branch和tag都是通过制作给定修订的一个虚拟副本（以另一个名字和/或另一个目录）创建的。在常规情况下，branch存储在branches目录下，tag位于tags目录下，尽管在实践中为了满足你的工程你可以使用自己的任何定制。

从Eclipse中，"Team>Branch/Tag"菜单能够使你创建branch和tag(参考图9)。其中，Browse按钮提供了一种方便的方法来查看有哪些branch和tag存在于仓库中。

当你使用"Team>Switch"创建成功一个新的branch或tag时，你可以非常容易地在branches之间进行切换。无论何时你切换到一个不同的branch(或返回到trunk)，Subversion将仅更新文件（它需要保持你的当前工作的副本与目的branch之间的同步）。


图9.创建一个新的branch或tag

(四) 修订历史

象大多数SCM系统一样，Subversion让你跟踪你的源码的变化。"Team>Show in Resource History"菜单选项能够使你查询这些变化的列表（包括对一个文件，目录或甚至整个工程的改变）(见图10)。

记住，在Subversion中，提交是原子性的-一次提交由一组文件变化和一个全局注释组成。"SVN Resource History"视图向你显示每一次提交的一个简明视图，包括修改的文件和相关注释。


图10.历史资源

四、结论

Subversion是一种强有力的和非常灵活的SCM工具，也是CVS的一个成功的后继者。结合Subclipse，Subversion能最终在你的Eclipse IDE环境中得到全面的发挥。