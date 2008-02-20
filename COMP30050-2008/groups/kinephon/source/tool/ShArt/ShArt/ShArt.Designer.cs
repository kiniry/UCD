namespace ShArt
{
	partial class ShArt
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if(disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.menuStrip1 = new System.Windows.Forms.MenuStrip();
			this.windowsToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.testToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.statusStrip1 = new System.Windows.Forms.StatusStrip();
			this.splPropertiesSplit = new System.Windows.Forms.Splitter();
			this.spcProperties = new System.Windows.Forms.SplitContainer();
			this.tvwShapes = new System.Windows.Forms.TreeView();
			this.pgdProperties = new System.Windows.Forms.PropertyGrid();
			this.splProperties = new System.Windows.Forms.Splitter();
			this.toolStrip1 = new System.Windows.Forms.ToolStrip();
			this.menuStrip1.SuspendLayout();
			this.spcProperties.Panel1.SuspendLayout();
			this.spcProperties.Panel2.SuspendLayout();
			this.spcProperties.SuspendLayout();
			this.SuspendLayout();
			// 
			// menuStrip1
			// 
			this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.windowsToolStripMenuItem});
			this.menuStrip1.Location = new System.Drawing.Point(0, 0);
			this.menuStrip1.Name = "menuStrip1";
			this.menuStrip1.Size = new System.Drawing.Size(473, 24);
			this.menuStrip1.TabIndex = 1;
			this.menuStrip1.Text = "menuStrip1";
			// 
			// windowsToolStripMenuItem
			// 
			this.windowsToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.testToolStripMenuItem});
			this.windowsToolStripMenuItem.Name = "windowsToolStripMenuItem";
			this.windowsToolStripMenuItem.Size = new System.Drawing.Size(62, 20);
			this.windowsToolStripMenuItem.Text = "Windows";
			// 
			// testToolStripMenuItem
			// 
			this.testToolStripMenuItem.Name = "testToolStripMenuItem";
			this.testToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
			this.testToolStripMenuItem.Text = "test";
			this.testToolStripMenuItem.Click += new System.EventHandler(this.testToolStripMenuItem_Click);
			// 
			// statusStrip1
			// 
			this.statusStrip1.Location = new System.Drawing.Point(0, 324);
			this.statusStrip1.Name = "statusStrip1";
			this.statusStrip1.RenderMode = System.Windows.Forms.ToolStripRenderMode.ManagerRenderMode;
			this.statusStrip1.Size = new System.Drawing.Size(473, 22);
			this.statusStrip1.TabIndex = 5;
			this.statusStrip1.Text = "stsStatus";
			this.statusStrip1.ItemClicked += new System.Windows.Forms.ToolStripItemClickedEventHandler(this.statusStrip1_ItemClicked);
			// 
			// splPropertiesSplit
			// 
			this.splPropertiesSplit.Dock = System.Windows.Forms.DockStyle.Right;
			this.splPropertiesSplit.Location = new System.Drawing.Point(260, 24);
			this.splPropertiesSplit.Name = "splPropertiesSplit";
			this.splPropertiesSplit.Size = new System.Drawing.Size(4, 300);
			this.splPropertiesSplit.TabIndex = 8;
			this.splPropertiesSplit.TabStop = false;
			this.splPropertiesSplit.SplitterMoved += new System.Windows.Forms.SplitterEventHandler(this.splPropertiesSplit_SplitterMoved);
			// 
			// spcProperties
			// 
			this.spcProperties.Location = new System.Drawing.Point(280, 24);
			this.spcProperties.Name = "spcProperties";
			this.spcProperties.Orientation = System.Windows.Forms.Orientation.Horizontal;
			// 
			// spcProperties.Panel1
			// 
			this.spcProperties.Panel1.Controls.Add(this.tvwShapes);
			// 
			// spcProperties.Panel2
			// 
			this.spcProperties.Panel2.Controls.Add(this.pgdProperties);
			this.spcProperties.Panel2.Resize += new System.EventHandler(this.spcProperties_Panel2_Resize);
			this.spcProperties.Size = new System.Drawing.Size(152, 248);
			this.spcProperties.SplitterDistance = 128;
			this.spcProperties.TabIndex = 7;
			// 
			// tvwShapes
			// 
			this.tvwShapes.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tvwShapes.Location = new System.Drawing.Point(0, 0);
			this.tvwShapes.Name = "tvwShapes";
			this.tvwShapes.Size = new System.Drawing.Size(152, 128);
			this.tvwShapes.TabIndex = 0;
			// 
			// pgdProperties
			// 
			this.pgdProperties.Location = new System.Drawing.Point(0, 0);
			this.pgdProperties.Name = "pgdProperties";
			this.pgdProperties.Size = new System.Drawing.Size(144, 104);
			this.pgdProperties.TabIndex = 0;
			// 
			// splProperties
			// 
			this.splProperties.Dock = System.Windows.Forms.DockStyle.Right;
			this.splProperties.Location = new System.Drawing.Point(264, 24);
			this.splProperties.Name = "splProperties";
			this.splProperties.Size = new System.Drawing.Size(209, 300);
			this.splProperties.TabIndex = 6;
			this.splProperties.TabStop = false;
			// 
			// toolStrip1
			// 
			this.toolStrip1.Dock = System.Windows.Forms.DockStyle.Left;
			this.toolStrip1.GripStyle = System.Windows.Forms.ToolStripGripStyle.Hidden;
			this.toolStrip1.Location = new System.Drawing.Point(0, 24);
			this.toolStrip1.Name = "toolStrip1";
			this.toolStrip1.RenderMode = System.Windows.Forms.ToolStripRenderMode.System;
			this.toolStrip1.Size = new System.Drawing.Size(26, 300);
			this.toolStrip1.TabIndex = 9;
			this.toolStrip1.Text = "toolStrip1";
			// 
			// ShArt
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(473, 346);
			this.Controls.Add(this.toolStrip1);
			this.Controls.Add(this.splPropertiesSplit);
			this.Controls.Add(this.spcProperties);
			this.Controls.Add(this.splProperties);
			this.Controls.Add(this.statusStrip1);
			this.Controls.Add(this.menuStrip1);
			this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.IsMdiContainer = true;
			this.MainMenuStrip = this.menuStrip1;
			this.Name = "ShArt";
			this.Text = "ShArt (v0.1)";
			this.Resize += new System.EventHandler(this.ShArt_Resize);
			this.Load += new System.EventHandler(this.ShArt_Load);
			this.menuStrip1.ResumeLayout(false);
			this.menuStrip1.PerformLayout();
			this.spcProperties.Panel1.ResumeLayout(false);
			this.spcProperties.Panel2.ResumeLayout(false);
			this.spcProperties.ResumeLayout(false);
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.MenuStrip menuStrip1;
		private System.Windows.Forms.ToolStripMenuItem windowsToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem testToolStripMenuItem;
		private System.Windows.Forms.StatusStrip statusStrip1;
		private System.Windows.Forms.Splitter splPropertiesSplit;
		private System.Windows.Forms.SplitContainer spcProperties;
		private System.Windows.Forms.TreeView tvwShapes;
		private System.Windows.Forms.PropertyGrid pgdProperties;
		private System.Windows.Forms.Splitter splProperties;
		private System.Windows.Forms.ToolStrip toolStrip1;
	}
}

